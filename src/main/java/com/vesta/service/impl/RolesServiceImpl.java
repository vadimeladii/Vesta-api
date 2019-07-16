package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.RoleRepository;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findByName(String name) {
        return roleRepository.findByName(name).orElseThrow
                (() -> new NotFoundException("The role doesn't exist"));
    }
}
