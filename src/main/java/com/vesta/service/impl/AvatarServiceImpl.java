package com.vesta.service.impl;

import com.vesta.exception.FileSizeException;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.AvatarEntity;
import com.vesta.service.AvatarService;
import com.vesta.service.converter.AvatarConverter;
import com.vesta.service.dto.AvatarDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository repository;
    private final UserRepository userRepository;
    private final AvatarConverter converter;

    @Transactional
    @Override
    public void uploadImage(Long userId, MultipartFile file) {
        log.info("method --- uploadImage");
        AvatarEntity entity;

        verify(!userRepository.existsById(userId), () -> new NotFoundException("User not found"));

        try {
            entity = new AvatarEntity();
            entity.setName(file.getOriginalFilename());
            entity.setUserEntity(userRepository.getOne(userId));
            entity.setAvatar(file.getBytes());
        } catch (IOException e) {
            throw new FileSizeException("File not correspond");
        }
        repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete");

        repository.deleteById(id);
    }

    @Override
    public AvatarDto getByUserId(Long userId) {
        log.info("method --- getByID");

        AvatarEntity entity = repository.findByUserEntity(userId).orElseThrow(
                () -> new NotFoundException("User image not found"));
        return converter.convert(entity);
    }

    @Override
    public ResponseEntity getAvatarByUserId(Long userId) {
        log.info("method --- getAvatarByUserId");

        AvatarEntity entity = repository.findByUserEntity(userId).orElseThrow(
                () -> new NotFoundException("User Avatar not found"));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-disposition", "attachment; filename=" + entity.getName());
        responseHeaders.add("Content-Type", MediaType.IMAGE_JPEG_VALUE);
        responseHeaders.add("Content-Type", MediaType.IMAGE_PNG_VALUE);

        ResponseEntity<byte[]> responseEntity;
        responseEntity = new ResponseEntity<>(entity.getAvatar(), responseHeaders, HttpStatus.OK);

        return responseEntity;
    }
}