package com.vesta.service.impl;

import com.vesta.repository.RoleRepository;
import com.vesta.service.RolesService;
import com.vesta.service.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    @Override
    public RoleDto findByName(String name){

        roleRepository.findByName(name).map(roleEntity -> ());

    }
}
