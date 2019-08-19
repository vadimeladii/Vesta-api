package com.vesta.controller.convertor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vesta.controller.view.SubjectView;
import com.vesta.exception.JsonException;
import com.vesta.service.dto.SubjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class SubjectViewConverter implements Converter<SubjectDto, SubjectView> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public SubjectView convert(SubjectDto dto) {
        if (dto == null) return null;
        SubjectView view = new SubjectView();
        view.setId(dto.getId());
        view.setPosition(List.of(dto.getPositionX(), dto.getPositionY()));
        view.setScale(dto.getScale());
        view.setRotation(dto.getRotation());
        view.setEditable(dto.getEditable());
        view.setFloorId(dto.getFloorId());
        view.setImage(dto.getImage());
        try {
            view.setAdditional(objectMapper.readValue(dto.getAdditional(), Object.class));
        } catch (IOException e) {
            throw new JsonException("Can not deserialize: " + dto.getAdditional());
        }

        return view;
    }

    public SubjectDto deconvert(SubjectView view) {
        if (view == null) return null;
        SubjectDto dto = new SubjectDto();
        dto.setId(view.getId());
        dto.setPositionX(view.getPosition().get(0));
        dto.setPositionY(view.getPosition().get(1));
        dto.setScale(view.getScale());
        dto.setEditable(view.getEditable());
        dto.setRotation(view.getRotation());
        dto.setFloorId(view.getFloorId());
        dto.setImage(view.getImage());
        try {
            dto.setAdditional(objectMapper.writeValueAsString(view.getAdditional()));
        } catch (JsonProcessingException e) {
            throw new JsonException("Can not process:" + view.getAdditional());
        }

        return dto;
    }
}
