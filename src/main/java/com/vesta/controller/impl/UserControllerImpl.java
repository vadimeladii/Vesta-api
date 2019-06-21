package com.vesta.controller.impl;

import com.vesta.controller.UserController;
import com.vesta.controller.convertor.UserViewConverter;
import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserView;
import com.vesta.service.UserService;
import com.vesta.service.dto.AccountCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    private final UserViewConverter userViewConverter;
  
    @ResponseBody
    public Map<String, Token> login(AccountCredential accountCredential) {
        return userService.login(accountCredential);
    }

    public UserView getById(Long id) {
        return userViewConverter.convert(userService.getById(id));
    }

    public List<UserView> findAll() {
        return userService.findAll()
                .stream()
                .map(userViewConverter::convert)
                .collect(Collectors.toList());
    }

    public void create(UserView userView) {
        userService.create(userViewConverter.deconvert(userView));
    }

    public UserView update(Long id, UserView userView) {
        return userViewConverter.convert(userService.update(id, userViewConverter.deconvert(userView)));
    }

    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public Token refreshToken(String refreshToken) {
        return userService.refreshToken(refreshToken);
    }
}