package com.vesta.controller.convertor;

import com.vesta.controller.view.SubjectTemplateView;
import com.vesta.service.dto.SubjectTemplateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectTemplateViewConverter implements Converter<SubjectTemplateDto, SubjectTemplateView> {

    @Override
    public SubjectTemplateView convert(SubjectTemplateDto dto) {
        if (dto == null) return null;
        SubjectTemplateView view = new SubjectTemplateView();
        view.setId(dto.getId());
        view.setImage(dto.getImage());
        return view;
    }

    public SubjectTemplateDto deconvert(SubjectTemplateView view) {
        if (view == null) return null;
        SubjectTemplateDto dto = new SubjectTemplateDto();
        dto.setId(view.getId());
        dto.setImage(view.getImage());
        return dto;
    }
}
