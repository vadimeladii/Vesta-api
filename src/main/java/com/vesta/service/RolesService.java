package com.vesta.service;

import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.dto.RoleDto;

public interface RolesService {

    RoleEntity findByName(String name);

    RoleDto findDtoByName(String nameOfRole);
}
