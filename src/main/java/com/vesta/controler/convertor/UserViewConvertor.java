
package com.vesta.controler.convertor;

import com.vesta.controler.view.UserView;
import com.vesta.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserViewConvertor implements Converter<UserDto, UserView> {

    @Override
    public UserView convert(UserDto dto) {
        if (dto == null) return null;
        UserView view = new UserView();
        view.setFirstName(dto.getFirstName());
        view.setLastName(dto.getLastName());
        view.setUsername(dto.getUsername());
        view.setEmail(dto.getEmail());
        view.setPassword(dto.getPassword());

        return view;
    }

    public UserDto deconvert(UserView userView){
        if (userView == null) return null;
        UserDto dto = new UserDto();
        dto.setFirstName(dto.getFirstName());
        dto.setLastName(dto.getLastName());
        dto.setUsername(dto.getUsername());
        dto.setPassword(dto.getPassword());
        dto.setEmail(dto.getEmail());

        return dto;
    }
}