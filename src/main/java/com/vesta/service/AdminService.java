package com.vesta.service;

import com.vesta.service.dto.UserDto;

public interface AdminService {

    UserDto update(Long id, UserDto userDto);

    void delete(Long id);
}
