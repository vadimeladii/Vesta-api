package com.vesta.controller;

import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserCreateView;
import com.vesta.controller.view.UserResetForgotView;
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
@Api(value = "User Controller REST Endpoint")
public interface UserController {

    @ApiOperation(value = "Returns the user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @ResponseBody
    @GetMapping("/{id}")
    UserView getById(@PathVariable Long id);

    @ApiOperation(value = "Returns all users in the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all users has succeeded"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @ResponseBody
    @GetMapping
    List<UserView> findAll();

    @ApiOperation(value = "Modify user data by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PutMapping("/{id}")
    UserCreateView update(@PathVariable("id") Long id, @RequestBody UserCreateView userCreateView);

    @ApiOperation(value = "Log on to the page")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login was success"),
            @ApiResponse(code = 401, message = "Username or password is not correct")
    })
    @PostMapping("/login")
    Map<String, String> login(@RequestBody AccountCredential accountCredential);


    @ApiOperation(value = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User was created"),
            @ApiResponse(code = 409, message = "Conflict username or email already exists"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserCreateView userCreateView);

    @ApiOperation(value = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @ApiOperation(value = "Refresh token")
    @PostMapping("/refresh")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Access Token was generated"),
            @ApiResponse(code = 401, message = "Life time of refresh token has finished")
    })
    Token refreshToken(@RequestBody Token refreshToken);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Email was sent successfully"),
            @ApiResponse(code = 401, message = "The email doesn't correct")
    })
    @ApiOperation(value = "Forgot password send email")
    @PostMapping("forgot/password/email")
    void forgotPasswordMail(String email);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Password was reset successfully"),
            @ApiResponse(code = 401, message = "Life time of token has finished"),
            @ApiResponse(code = 409, message = "New Password equals with Old Password")
    })
    @ApiOperation(value = "Reset forgot password")
    @PostMapping("reset/forgot/password")
    void resetForgotPassword(@RequestBody UserResetForgotView userResetForgotView);

    @ApiOperation(value = "Return current authenticated user")
    @GetMapping(value = "/me")
    @ResponseBody
    UserView currentUserDetails();
}