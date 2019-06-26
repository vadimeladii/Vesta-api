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
        if (entity == null) return null;
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRoles(entity.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()));

        return dto;
    }

    public UserEntity deconvert(UserDto userDto) {
        if (userDto == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setEmail(userDto.getEmail());

        return userEntity;
    }
}