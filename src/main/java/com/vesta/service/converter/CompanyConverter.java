package com.vesta.service.converter;

import com.vesta.repository.entity.CompanyEntity;
import com.vesta.service.dto.CompanyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyConverter implements Converter<CompanyEntity, CompanyDto> {

    @Override
    public CompanyDto convert(CompanyEntity entity) {
        if (entity == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFloor(entity.getFloor());
        return dto;
    }
}
