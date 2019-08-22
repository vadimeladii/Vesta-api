package com.vesta.controller.convertor;

import com.vesta.controller.view.UserUpdateView;
import com.vesta.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateViewConverter implements Converter<UserDto, UserUpdateView> {

    @Override
    public UserUpdateView convert(UserDto dto) {
        if (dto == null) return null;
        UserUpdateView view = new UserUpdateView();
        view.setFirstName(dto.getFirstName());
        view.setLastName(dto.getLastName());
        view.setEmail(dto.getEmail());

        return view;
    }

    public UserDto deconvert(UserUpdateView view) {
        if (view == null) return null;
        UserDto dto = new UserDto();
        dto.setFirstName(view.getFirstName());
        dto.setLastName(view.getLastName());
        dto.setEmail(view.getEmail());

        return dto;
    }
}
