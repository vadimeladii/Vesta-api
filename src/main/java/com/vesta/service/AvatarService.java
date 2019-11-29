package com.vesta.service;

import com.vesta.service.dto.AvatarDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AvatarService {

    void uploadImage(Long userId, MultipartFile file);

    void delete(Long id);

    AvatarDto getByUserId(Long userId);

    ResponseEntity getAvatarByUserId(Long userId);

    List<AvatarDto> findAll();
}