package com.vesta.controller.convertor;

import com.vesta.controller.view.UserCreateView;
import com.vesta.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCreateViewConverter implements Converter<UserDto, UserCreateView> {

    @Override
    public UserCreateView convert(UserDto dto) {
        if (dto == null) return null;
        UserCreateView view = new UserCreateView();
        view.setFirstName(dto.getFirstName());
        view.setLastName(dto.getLastName());
        view.setUsername(dto.getUsername());
        view.setPassword(dto.getPassword());
        view.setEmail(dto.getEmail());

        return view;
    }

    public UserDto deconvert(UserCreateView view) {
        if (view == null) return null;
        UserDto dto = new UserDto();
        dto.setFirstName(view.getFirstName());
        dto.setLastName(view.getLastName());
        dto.setUsername(view.getUsername());
        dto.setPassword(view.getPassword());
        dto.setEmail(view.getEmail());

        return dto;
    }
}