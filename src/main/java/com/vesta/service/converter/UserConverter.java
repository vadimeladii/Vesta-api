package com.vesta.service.converter;

import com.vesta.repository.entity.RoleEntity;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity entity) {
        if(entity == null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRoles(entity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()));
        return dto;
    }
}