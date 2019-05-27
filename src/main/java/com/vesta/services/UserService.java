package com.vesta.services;

import com.vesta.controller.view.Token;
import com.vesta.repositry.AccountCredentials;
import com.vesta.repositry.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public Token login(AccountCredentials accountCredentials) {
//        if(userRepository.existsByUsernameAndPassword(accountCredentials.getUsername(), accountCredentials.getPassword()))
            return tokenService.generatedToken(accountCredentials.getUsername());

//        return null;
    }

//    private Boolean exist(AccountCredentials accountCredentials){
//
//    }
}
