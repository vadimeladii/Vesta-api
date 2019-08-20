package com.vesta.controller.impl;

import com.vesta.controller.AvatarController;
import com.vesta.controller.convertor.AvatarViewConverter;
import com.vesta.controller.view.AvatarView;
import com.vesta.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AvatarControllerImpl implements AvatarController {

    private final AvatarService service;
    private final AvatarViewConverter converter;

    @Override
    public void uploadImage(Long userId, MultipartFile file) {
        service.uploadImage(userId, file);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public AvatarView getByUserId(Long userId) {
        return converter.convert(service.getByUserId(userId));
    }

    @Override
    public ResponseEntity getAvatarByUserId(Long userId) {
        return service.getAvatarByUserId(userId);
    }
}
