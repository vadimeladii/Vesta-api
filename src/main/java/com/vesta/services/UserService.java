package com.vesta.services;

import com.vesta.controller.view.Token;
import com.vesta.repositry.AccountCredentials;
import com.vesta.repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public Token login(AccountCredentials accountCredentials) {
        return tokenService.generatedToken(accountCredentials);
    }

    private Boolean exist(AccountCredentials accountCredentials){

    }
}
