package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.RoleRepository;
import com.vesta.service.RolesService;
import com.vesta.service.converter.RoleConvertor;
import com.vesta.service.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RoleRepository roleRepository;

    private final RoleConvertor roleConvertor;

    @Override
    public RoleDto findByName(String name){

        return roleRepository.findByName(name).map(roleConvertor::convert).orElseThrow
                (()-> new NotFoundException("The role doesn't exist"));
    }
}
