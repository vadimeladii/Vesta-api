package com.vesta.controller.convertor;

import com.vesta.controller.view.AvatarView;
import com.vesta.service.dto.AvatarDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AvatarViewConverter implements Converter<AvatarDto, AvatarView> {

    @Override
    public AvatarView convert(AvatarDto dto) {
        if (dto == null) return null;
        AvatarView view = new AvatarView();
        view.setId(dto.getId());
        view.setAvatar(dto.getAvatar());
        view.setName(dto.getName());

        return view;
    }
}
