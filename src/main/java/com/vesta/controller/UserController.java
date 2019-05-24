package com.vesta.controller;

import com.vesta.controller.view.Token;
import com.vesta.repositry.AccountCredentials;
import com.vesta.services.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
@Api(tags = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Token login(AccountCredentials accountCredentials) {
        return userService.login(accountCredentials);
    }
}