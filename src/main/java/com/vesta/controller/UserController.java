package com.vesta.controller;

import com.vesta.controller.convertor.UserViewConverter;
import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserView;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserViewConverter userViewConverter;

    @PostMapping("/login")
    public Token login(@RequestBody AccountCredential accountCredential) {
        return userService.login(accountCredential);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('user')")
    public UserView getById(@PathVariable Long id){
        SecurityContextHolder.getContext().getAuthentication();
        return userViewConverter.convert(userService.getById(id));
    }
}