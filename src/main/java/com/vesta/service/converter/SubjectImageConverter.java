package com.vesta.service.converter;

import com.vesta.repository.entity.SubjectImageEntity;
import com.vesta.service.dto.SubjectImageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectImageConverter implements Converter<SubjectImageEntity, SubjectImageDto> {

    @Override
    public SubjectImageDto convert(SubjectImageEntity entity) {
        if (entity == null) return null;
        SubjectImageDto dto = new SubjectImageDto();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        return dto;
    }
    public SubjectImageEntity deconvert(SubjectImageDto imageDto) {
        if (imageDto == null) return null;
        SubjectImageEntity imageEntity = new SubjectImageEntity();
        imageEntity.setImage(imageDto.getImage());
        return imageEntity;
    }
}
