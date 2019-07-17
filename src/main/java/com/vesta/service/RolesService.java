package com.vesta.service;

import com.vesta.repository.entity.RoleEntity;

public interface RolesService {

    RoleEntity findByName(String name);
}
