package com.vesta.service.converter;

import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleConvertor implements Converter<RoleEntity, RoleDto> {

    @Override
    public RoleDto convert(RoleEntity entity) {
        if (entity == null) return null;
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    public RoleEntity deconvert(RoleDto userDto) {
        if (userDto == null) return null;
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(userDto.getId());
        roleEntity.setName(userDto.getName());

        return roleEntity;
    }

}
