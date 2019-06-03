package com.vesta.service;

import com.vesta.controller.view.Token;
import com.vesta.repository.UserRepository;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private TokenService tokenService;

    public UserDto getById(Long id) {
        return userConverter.convert(userRepository.findById(id).orElse(null));
    }

    public UserDto getByUsername(String username) {
        return userConverter.convert(userRepository.findByUsername(username).orElse(null));
    }

    public Token login(AccountCredential accountCredential) {
        if (userRepository.existsByUsernameOrEmailAndPassword(
                accountCredential.getUsername(),
                accountCredential.getEmail(),
                accountCredential.getPassword()))
            return tokenService.generatedToken(accountCredential.getUsername());


        return null;
    }
}