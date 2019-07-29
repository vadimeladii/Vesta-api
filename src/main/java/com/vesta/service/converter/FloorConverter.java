package com.vesta.service.converter;

import com.vesta.repository.entity.FloorEntity;
import com.vesta.service.dto.FloorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FloorConverter implements Converter<FloorEntity, FloorDto> {

    @Override
    public FloorDto convert(FloorEntity entity) {
        if (entity == null) return null;
        FloorDto dto = new FloorDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCompanyId(entity.getCompanyId());

        return dto;
    }

    public FloorEntity deconvert(FloorDto dto) {
        if (dto == null) return null;
        FloorEntity entity = new FloorEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCompanyId(dto.getCompanyId());

        return entity;
    }
}