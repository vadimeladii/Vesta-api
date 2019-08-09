package com.vesta.service.impl;

import com.vesta.exception.FileSizeException;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.AvatarRepository;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.AvatarEntity;
import com.vesta.service.AvatarService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository repository;
    private final UserRepository userRepository;

    @Override
    public void uploadImage(Long userId, MultipartFile file) {
        log.info("method --- uploadImage");

        verify(userRepository.existsById(userId), ()-> new NotFoundException("User not found"));

            AvatarEntity entity = new AvatarEntity();
            entity.setName(file.getName());
            entity.setUserEntity(userRepository.getOne(userId));
            try {
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
}