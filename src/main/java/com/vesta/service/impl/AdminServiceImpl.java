package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.UserRepository;
import com.vesta.repository.entity.UserEntity;
import com.vesta.service.AdminService;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDto update(Long id, UserDto userDto) {
        log.info("method --- update");

        UserEntity entity = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User doesnt exist"));

        UserEntity userUpdated = userConverter.deconvert(userDto);
        entity.setFirstName(userUpdated.getFirstName());
        entity.setLastName(userUpdated.getLastName());
        entity.setEmail(userUpdated.getEmail());
        return userConverter.convert(userRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete");

        userRepository.deleteById(id);
    }
}
