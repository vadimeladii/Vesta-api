package com.vesta.service.converter;

import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.dto.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubjectConverter implements Converter<SubjectEntity, SubjectDto> {

    private final SubjectTemplateRepository templateRepository;

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
        dto.setAdditional(entity.getAdditional());

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
        entity.setSubjectTemplateEntity(getTemplateEntity(dto));
        entity.setAdditional(dto.getAdditional());

        return entity;
    }

    private SubjectTemplateEntity getTemplateEntity(SubjectDto dto) {

        SubjectTemplateEntity subjectTemplateEntity;
        Optional<SubjectTemplateEntity> templateEntity = templateRepository.getByImage(dto.getImage());

        if (templateEntity.isEmpty()) {
            subjectTemplateEntity = new SubjectTemplateEntity();
            subjectTemplateEntity.setImage(dto.getImage());
            subjectTemplateEntity = templateRepository.save(subjectTemplateEntity);
        } else {
            subjectTemplateEntity = templateEntity.get();
        }
        return subjectTemplateEntity;
    }
}
