package com.vesta.service.impl;

import com.vesta.config.security.SecurityConfig;
import com.vesta.controller.view.Token;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.UserService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final TokenServiceImpl tokenService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto getById(Long id) {
        return userConverter.convert(userRepository.findById(id).orElse(null));
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
        entity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(entity);
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
        userEntity.setPassword(passwordEncoder.encode(userUpdated.getPassword()));
        userEntity.setUsername(userUpdated.getUsername());
        userEntity.setEmail(userUpdated.getEmail());
        return userConverter.convert(userRepository.save(userEntity));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto getByUsername(String username) {
        return userConverter.convert(userRepository.findByUsername(username).orElse(null));
    }

    public Token login(AccountCredential accountCredential) {
        if (userRepository.existsByUsernameOrEmailAndPassword(
                accountCredential.getUsername(),
                accountCredential.getEmail(),
                passwordEncoder.encode(accountCredential.getPassword())))
            return tokenService.generatedToken(accountCredential.getUsername());

        return null;
    }

}