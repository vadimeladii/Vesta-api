package com.vesta.service;

import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {

    void uploadImage(Long userId, MultipartFile file);

    void delete(Long id);
}
