package com.vesta.service;

import com.vesta.controller.view.Token;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
public interface UserService extends UserDetailsService {

    UserDto getById(Long id);

    List<UserDto> findAll();

    void create(@Valid UserDto userDto);

    UserDto update(Long id,@Valid UserDto userDto);

    void delete(Long id);

    UserDto getByUsername(String username);

    Map<String, String> login(AccountCredential accountCredential);

    Token refreshToken(String refreshToken);
}