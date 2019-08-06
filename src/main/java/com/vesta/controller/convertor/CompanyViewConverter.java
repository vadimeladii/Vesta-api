package com.vesta.controller.convertor;

import com.vesta.controller.view.CompanyView;
import com.vesta.service.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompanyViewConverter implements Converter<CompanyDto, CompanyView> {

    private final FloorViewConverter floorViewConverter;

    @Override
    public CompanyView convert(CompanyDto dto) {
        if (dto == null) return null;
        CompanyView view = new CompanyView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setFloors(dto.getFloors().stream().map(floorViewConverter::convert).collect(Collectors.toList()));
        return view;
    }

    public CompanyDto deconvert(CompanyView view) {
        if (view == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setName(view.getName());
        dto.setFloors(view.getFloors().stream().map(floorViewConverter::deconvert).collect(Collectors.toList()));
        return dto;
    }
}
