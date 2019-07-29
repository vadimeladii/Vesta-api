package com.vesta.service.converter;

import com.vesta.repository.entity.CompanyEntity;
import com.vesta.service.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyConverter implements Converter<CompanyEntity, CompanyDto> {

    private final FloorConverter floorConverter;

    @Override
    public CompanyDto convert(CompanyEntity entity) {
        if (entity == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFloors(entity.getFloors().stream().map(floorConverter::convert).collect(Collectors.toList()));
        return dto;
    }
}
