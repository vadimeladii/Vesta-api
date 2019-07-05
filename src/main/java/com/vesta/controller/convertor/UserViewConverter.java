package com.vesta.controller.convertor;

import com.vesta.controller.view.UserView;
import com.vesta.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserViewConverter implements Converter<UserDto, UserView> {

    @Override
    public UserView convert(UserDto dto) {
        if (dto == null) return null;
        UserView view = new UserView();
        view.setId(dto.getId());
        view.setFirstName(dto.getFirstName());
        view.setLastName(dto.getLastName());
        view.setUsername(dto.getUsername());
        view.setEmail(dto.getEmail());

        return view;
    }
}
