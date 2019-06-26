package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.exception.BadRequestException;
import com.vesta.exception.NotFoundException;
import com.vesta.exception.VestaException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.TokenService;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final TokenService tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The user doesn't exist"));
        return userConverter.convert(userEntity);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(UserDto userDto) {
        UserEntity entity = userConverter.deconvert(userDto);
        if (!userRepository.existsByUsername(entity.getUsername())) {
            entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userRepository.save(entity);
            throw new VestaException("Username already exists", HttpStatus.CONFLICT);
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("The user doesn't exist"));

        UserEntity userUpdated = userConverter.deconvert(userDto);
        userEntity.setFirstName(userUpdated.getFirstName());
        userEntity.setLastName(userUpdated.getLastName());
        userEntity.setPassword(passwordEncoder.encode(userUpdated.getPassword()));
        userEntity.setUsername(userUpdated.getUsername());
        userEntity.setEmail(userUpdated.getEmail());
        return userConverter.convert(userRepository.save(userEntity));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getByUsername(String username) {
        return userConverter.convert(getUserEntityByUsername(username,
                new NotFoundException("The username not found")));
    }

    @Override
    public Map<String, Token> login(AccountCredential accountCredential) {

        UserEntity userEntity = getUserEntityByUsername(accountCredential.getUsername(),
                new NotFoundException("The username or email doesn't exist"));

        if (!passwordEncoder.matches(accountCredential.getPassword(), userEntity.getPassword())) {
            throw new BadRequestException("The password does not correct");
        }

        Map<String, Token> tokens = new HashMap<>();
        tokens.put("accessToken", tokenService.generatedAccessToken(accountCredential.getUsername()));
        tokens.put("refreshToken", tokenService.generatedRefreshToken(accountCredential.getUsername()));
        return tokens;
    }

    @Override
    public Token refreshToken(String refreshToken) {
        return tokenService.generatedAccessToken(tokenService.getRefreshSubject(refreshToken));
    }

    private UserEntity getUserEntityByUsername(String username, VestaException exception) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> exception);
    }
}