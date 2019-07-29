package com.vesta.service.converter;

import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.dto.SubjectTemplateDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectTemplateConverter implements Converter<SubjectTemplateEntity, SubjectTemplateDto> {

    @Override
    public SubjectTemplateDto convert(SubjectTemplateEntity entity) {
        if (entity == null) return null;
        SubjectTemplateDto dto = new SubjectTemplateDto();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        return dto;
    }

    public SubjectTemplateEntity deconvert(SubjectTemplateDto imageDto) {
        if (imageDto == null) return null;
        SubjectTemplateEntity imageEntity = new SubjectTemplateEntity();
        imageEntity.setImage(imageDto.getImage());
        return imageEntity;
    }
}
