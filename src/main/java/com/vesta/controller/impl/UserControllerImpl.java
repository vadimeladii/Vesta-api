package com.vesta.controller.impl;

import com.vesta.controller.UserController;
import com.vesta.controller.convertor.UserViewConverter;
import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserView;
import com.vesta.service.UserService;
import com.vesta.service.dto.AccountCredential;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserViewConverter userViewConverter;

    @ApiOperation(value = "Logarea pe pagina")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Login was success"),
            @ApiResponse(code = 404, message = "Username, password or email not found")
    })
    public Map<String, Token> login(AccountCredential accountCredential) {
        return userService.login(accountCredential);
    }

    @ApiOperation(value = "Returneaza userul dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get user by id has succeeded"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public UserView getById(Long id) {
        return userViewConverter.convert(userService.getById(id));
    }

    @ApiOperation(value = "Returneaza toti userii din baza de date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all users has succeeded"),
            @ApiResponse(code = 404, message = "Users not found")
    })
    public List<UserView> findAll() {
        return userService.findAll()
                .stream()
                .map(userViewConverter::convert)
                .collect(Collectors.toList());
    }
    @ApiOperation(value = "Creaza un nou user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was created"),
            @ApiResponse(code = 500, message = "Server error")
    })
    public void create(UserView userView) {
        userService.create(userViewConverter.deconvert(userView));
    }
    @ApiOperation(value = "Modifica datele userului dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public UserView update(Long id, UserView userView) {
        return userViewConverter.convert(userService.update(id, userViewConverter.deconvert(userView)));
    }
    @ApiOperation(value = "Sterge userul dupa ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User was deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public void delete(Long id) {
        userService.delete(id);
    }

    @ApiOperation(value = "Refresh token")
    @Override
    public Token refreshToken(String refreshToken) {
        return userService.refreshToken(refreshToken);
    }
}