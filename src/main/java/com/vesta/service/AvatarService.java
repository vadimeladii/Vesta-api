package com.vesta.service;

import com.vesta.service.dto.AvatarDto;
import org.springframework.web.multipart.MultipartFile;

public interface AvatarService {

    void uploadImage(Long userId, MultipartFile file);

    void delete(Long id);

    AvatarDto getByUserId(Long userId);
}
