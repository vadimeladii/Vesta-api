package com.vesta.controller.convertor;

import org.springframework.core.convert.converter.Converter;
import com.vesta.controller.view.CompanyUpdateView;
import com.vesta.service.dto.CompanyDto;
import org.springframework.stereotype.Component;

@Component
public class CompanyUpdateViewConverter implements Converter<CompanyDto, CompanyUpdateView> {

    @Override
    public CompanyUpdateView convert(CompanyDto dto) {
        if(dto == null) return null;
        CompanyUpdateView updateView = new CompanyUpdateView();
        updateView.setName(dto.getName());

        return updateView;
    }

    public CompanyDto deconvert(CompanyUpdateView updateView){
        if(updateView == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setName(updateView.getName());

        return dto;
    }
}
