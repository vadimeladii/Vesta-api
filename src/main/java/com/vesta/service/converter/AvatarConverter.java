package com.vesta.service.converter;

import com.vesta.repository.entity.AvatarEntity;
import com.vesta.service.dto.AvatarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvatarConverter implements Converter<AvatarEntity, AvatarDto> {


    @Override
    public AvatarDto convert(AvatarEntity entity) {
        if (entity == null) return null;
        AvatarDto dto = new AvatarDto();
        dto.setId(entity.getId());
        dto.setAvatar(entity.getAvatar());
        dto.setName(entity.getName());
        dto.setUserId(entity.getUserEntity().getId());

        return dto;
    }
}
