package com.vesta.service.converter;

import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//used for authentification process
@Component
public class RoleConverter implements Converter<RoleEntity, RoleDto> {
    @Override
    public RoleDto convert(RoleEntity entity) {
        if(entity == null) return null;
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());

        return dto;
    }

    public RoleEntity deconvert(RoleDto dto) {
        if(dto == null) return null;
        RoleEntity entity = new RoleEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }
}
