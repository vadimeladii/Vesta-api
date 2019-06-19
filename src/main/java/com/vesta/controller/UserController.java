package com.vesta.controller;

import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserView;
import com.vesta.service.dto.AccountCredential;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/user")
@Api(value = "User Controller REST Endpoint", description = "Shows the user info")
public interface UserController {

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login was success"),
            @ApiResponse(code = 404, message = "Username, password or email not found")
    })
    @PostMapping("/login")
    Map<String, Token> login(@RequestBody AccountCredential accountCredential);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/{id}")
    UserView getById(@PathVariable Long id);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all users has succeeded"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @GetMapping
    List<UserView> findAll();

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserView userView);

    @PutMapping("/{id}")
    UserView update(@PathVariable("id") Long id, @RequestBody UserView userView);

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id);

    @PostMapping("/refresh")
    Token refreshToken(String refreshToken);
}