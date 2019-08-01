package com.vesta.controller.convertor;

import com.vesta.controller.view.SubjectView;
import com.vesta.service.dto.SubjectDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectViewConverter implements Converter<SubjectDto, SubjectView> {

    @Override
    public SubjectView convert(SubjectDto dto) {
        if (dto == null) return null;
        SubjectView view = new SubjectView();
        view.setId(dto.getId());
        view.setPosition(List.of(dto.getPositionX(), dto.getPositionY()));
        view.setScale(dto.getScale());
        view.setRotation(dto.getRotation());
        view.setEditable(dto.getEditable());
        view.setImage(dto.getImage());

        return view;
    }

    public SubjectDto deconvert(SubjectView view) {
        if (view == null) return null;
        SubjectDto dto = new SubjectDto();
        dto.setId(view.getId());
        dto.setPositionX(view.getPosition().get(0));
        dto.setPositionY(view.getPosition().get(1));
        dto.setScale(view.getScale());
        dto.setRotation(view.getRotation());
        dto.setImage(view.getImage());

        return dto;
    }
}
