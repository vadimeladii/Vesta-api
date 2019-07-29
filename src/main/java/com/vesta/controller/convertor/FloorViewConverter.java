package com.vesta.controller.convertor;

import com.vesta.controller.view.FloorView;
import com.vesta.service.dto.FloorDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FloorViewConverter implements Converter<FloorDto, FloorView> {

    @Override
    public FloorView convert(FloorDto dto) {
        if (dto == null) return null;
        FloorView view = new FloorView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setCompanyId(dto.getCompanyId());

        return view;
    }

    public FloorDto deconvert(FloorView view) {
        if (view == null) return null;
        FloorDto dto = new FloorDto();
        dto.setId(view.getId());
        dto.setName(view.getName());
        dto.setCompanyId(view.getCompanyId());

        return dto;
    }
}