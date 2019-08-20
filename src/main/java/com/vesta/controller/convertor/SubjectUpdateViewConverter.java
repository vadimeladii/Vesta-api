package com.vesta.controller.convertor;

import com.vesta.controller.view.SubjectUpdateView;
import com.vesta.service.dto.SubjectDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectUpdateViewConverter implements Converter<SubjectDto, SubjectUpdateView> {

    @Override
    public SubjectUpdateView convert(SubjectDto dto) {
        if (dto == null) return null;
        SubjectUpdateView view = new SubjectUpdateView();
        view.setPosition(List.of(dto.getPositionX(), dto.getPositionY()));
        view.setScale(dto.getScale());
        view.setRotation(dto.getRotation());
        view.setAdditional(dto.getAdditional());

        return view;
    }

    public SubjectDto deconvert(SubjectUpdateView view) {
        if (view == null) return null;
        SubjectDto dto = new SubjectDto();
        dto.setPositionX(view.getPosition().get(0));
        dto.setPositionY(view.getPosition().get(1));
        dto.setScale(view.getScale());
        dto.setRotation(view.getRotation());
        dto.setAdditional(view.getAdditional());

        return dto;
    }
}
