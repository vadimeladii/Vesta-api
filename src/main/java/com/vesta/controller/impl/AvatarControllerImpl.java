package com.vesta.controller.impl;

import com.vesta.controller.AvatarController;
import com.vesta.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class AvatarControllerImpl implements AvatarController {

    private final AvatarService service;

    @Override
    public void uploadImage(Long userId, MultipartFile file) {
        service.uploadImage(userId, file);
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
