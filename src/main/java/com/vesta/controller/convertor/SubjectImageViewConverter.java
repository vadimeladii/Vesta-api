package com.vesta.controller.convertor;

import com.vesta.controller.view.SubjectImageView;
import com.vesta.service.dto.SubjectImageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectImageViewConverter implements Converter<SubjectImageDto, SubjectImageView> {

    @Override
    public SubjectImageView convert(SubjectImageDto dto) {
        if (dto ==  null) return null;
        SubjectImageView view = new SubjectImageView();
        view.setId(dto.getId());
        view.setImage(dto.getImage());
        return view;
    }

    public SubjectImageDto deconvert(SubjectImageView view){
        if (view == null) return null;
        SubjectImageDto dto = new SubjectImageDto();
        dto.setId(view.getId());
        dto.setImage(view.getImage());
        return dto;
    }
}
