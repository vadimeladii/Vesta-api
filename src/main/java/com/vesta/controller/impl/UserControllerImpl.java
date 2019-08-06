package com.vesta.controller.impl;

import com.vesta.controller.UserController;
import com.vesta.controller.convertor.UserCreateViewConverter;
import com.vesta.controller.convertor.UserViewConverter;
import com.vesta.controller.view.Token;
import com.vesta.controller.view.UserCreateView;
import com.vesta.controller.view.UserResetForgotView;
import com.vesta.controller.view.UserView;
import com.vesta.service.UserService;
import com.vesta.service.dto.AccountCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserCreateViewConverter userCreateViewConverter;
    private final UserViewConverter userViewConverter;

    @Override
    public UserView getById(Long id) {
        return userViewConverter.convert(userService.getById(id));
    }

    @Override
    public List<UserView> findAll() {
        return userService.findAll()
                .stream()
                .map(userViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public UserCreateView update(Long id, UserCreateView userCreateView) {
        return userCreateViewConverter.convert(userService.update(id, userCreateViewConverter.deconvert(userCreateView)));
    }

    @Override
    public Map<String, String> login(AccountCredential accountCredential) {
        return userService.login(accountCredential);
    }

    @Override
    public void create(UserCreateView userCreateView) {
        userService.create(userCreateViewConverter.deconvert(userCreateView));
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public Token refreshToken(Token refreshToken) {
        return userService.refreshToken(refreshToken.getJwtToken());
    }

    @Override
    public void forgotPasswordMail(String email) {
        userService.forgotPasswordMail(email);
    }

    @Override
    public void resetForgotPassword(UserResetForgotView userResetForgotView) {
        userService.resetForgotPassword(userResetForgotView.getToken(), userResetForgotView.getPassword());
    }

    @Override
    public UserView currentUserDetails() {
        return userViewConverter.convert(userService.getByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
    }
}