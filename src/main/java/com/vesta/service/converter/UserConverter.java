package com.vesta.service.converter;

import com.vesta.repository.entity.UserEntity;
import com.vesta.service.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto converter(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        return dto;
    }
}