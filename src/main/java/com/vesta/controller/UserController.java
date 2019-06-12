package com.vesta.controller;

import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserView;
import com.vesta.service.dto.AccountCredential;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
public interface UserController {

    @PostMapping("/login")
    Token login(@RequestBody AccountCredential accountCredential);

    @GetMapping("/{id}")
    UserView getById(@PathVariable Long id);

    @GetMapping
    List<UserView> findAll();

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserView userView);

    @PutMapping("/{id}")
    UserView update(@PathVariable("id") Long id, @RequestBody UserView userView);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);
}