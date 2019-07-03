package com.vesta.controller.convertor;

import com.vesta.controller.view.CompanyView;
import com.vesta.service.dto.CompanyDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyViewConverter implements Converter<CompanyDto, CompanyView> {

    @Override
    public CompanyView convert(CompanyDto dto){
        if(dto == null) return null;
        CompanyView view = new CompanyView();
        view.setId(dto.getId());
        view.setName(dto.getName());
        view.setFloor(dto.getFloor());
        return view;
    }
}
