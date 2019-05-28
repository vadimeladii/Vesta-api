
package com.vesta.controler.convertor;

import com.vesta.controler.view.UserView;
import com.vesta.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserViewConvertor {

    public UserView convertor (UserDto dto){
        if(dto == null) return null;
        UserView view = new UserView();
        view.setUsername(dto.getUsername());
        view.setEmail(dto.getEmail());
        view.setPassword(dto.getPassword());

        return view;
    }
}