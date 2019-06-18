package com.vesta.service.impl;

import com.vesta.controller.view.Token;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
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

    private final TokenServiceImpl tokenService;

    @Override
    public UserDto getById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new NotFoundException("The user doesn't exist"));
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
        userRepository.save(userConverter.deconvert(userDto));
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            return null;
        }

        UserEntity userUpdated = userConverter.deconvert(userDto);
        userEntity.setFirstName(userUpdated.getFirstName());
        userEntity.setLastName(userUpdated.getLastName());
        userEntity.setPassword(userUpdated.getPassword());
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
        return userConverter.convert(userRepository.findByUsername(username).orElse(null));
    }

    public Map<String, Token> login(AccountCredential accountCredential) {
        if (userRepository.existsByUsernameOrEmailAndPassword(
                accountCredential.getUsername(),
                accountCredential.getEmail(),
                accountCredential.getPassword())) {
            Map<String, Token> tokens = new HashMap<>();
            tokens.put("accessToken", tokenService.generatedAccessToken(accountCredential.getUsername()));
            tokens.put("refreshToken", tokenService.generatedRefreshToken(accountCredential.getUsername()));
            return tokens;
        }
        return null;
    }

    @Override
    public Token refreshToken(String refreshToken) {
        return tokenService.generatedAccessToken(tokenService.getRefreshSubject(refreshToken));
    }
}