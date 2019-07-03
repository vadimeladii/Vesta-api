package com.vesta.controller;

import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserCreateView;
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

    @ApiOperation(value = "Returneaza userul dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @ResponseBody
    @GetMapping("/{id}")
    UserView getById(@PathVariable Long id);

    @ApiOperation(value = "Returneaza toti userii din baza de date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all users has succeeded"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    @ResponseBody
    @GetMapping
    List<UserView> findAll();

    @ApiOperation(value = "Modifica datele userului dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @PutMapping("/{id}")
    UserCreateView update(@PathVariable("id") Long id, @RequestBody UserCreateView userCreateView);

    @ApiOperation(value = "Logarea pe pagina")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login was success"),
            @ApiResponse(code = 401, message = "Username or password is not correct")
    })
    @PostMapping("/login")
    Map<String, String> login(@RequestBody AccountCredential accountCredential) throws Exception;


    @ApiOperation(value = "Creaza un nou user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User was created"),
            @ApiResponse(code = 409, message = "Conflict username or eamil already exists"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody UserCreateView userCreateView);

    @ApiOperation(value = "Sterge userul dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);

    @ApiOperation(value = "Refresh token")
    @PostMapping("/refresh")
    Token refreshToken(String refreshToken);
}