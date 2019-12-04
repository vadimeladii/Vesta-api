package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.RoleRepository;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.service.RolesService;
import com.vesta.service.converter.RoleConverter;
import com.vesta.service.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Override
    public RoleEntity findByName(String name) {
        log.info("method --- findByName");

        return roleRepository.findByName(name).orElseThrow
                (() -> new NotFoundException("The role doesn't exist"));
    }

    @Override
    public RoleDto findDtoByName(String nameOfRole){
        log.info("method --- findDtoByName");

        return roleConverter.convert(roleRepository.findByName(nameOfRole).orElseThrow
                (() -> new NotFoundException("The role doesn't exist")));
    }
}
