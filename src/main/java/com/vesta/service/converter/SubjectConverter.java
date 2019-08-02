package com.vesta.service.converter;

import com.vesta.repository.entity.SubjectEntity;
import com.vesta.service.dto.SubjectDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements Converter<SubjectEntity, SubjectDto> {

    @Override
    public SubjectDto convert(SubjectEntity entity) {
        if (entity == null) return null;
        SubjectDto dto = new SubjectDto();
        dto.setId(entity.getId());
        dto.setPositionX(entity.getPositionX());
        dto.setPositionY(entity.getPositionY());
        dto.setScale(entity.getScale());
        dto.setEditable(entity.getEditable());
        dto.setRotation(entity.getRotation());
        dto.setFloorId(entity.getFloorId());
        dto.setImage(entity.getSubjectTemplateEntity().getImage());

        return dto;
    }

    public SubjectEntity deconvert(SubjectDto dto) {
        if (dto == null) return null;
        SubjectEntity entity = new SubjectEntity();
        entity.setPositionX(dto.getPositionX());
        entity.setPositionY(dto.getPositionY());
        entity.setScale(dto.getScale());
        entity.setEditable(dto.getEditable());
        entity.setRotation(dto.getRotation());
        entity.setFloorId(dto.getFloorId());

        return entity;
    }
}
