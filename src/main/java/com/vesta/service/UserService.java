
package com.vesta.service;

import com.vesta.repository.UserRepository;
import com.vesta.service.converter.UserConverter;
import com.vesta.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    public UserDto getById(Long id) {
        return userConverter.converter(userRepository.findById(id).orElseThrow(null));
    }
}