package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.exception.VestaException;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.service.CompanyService;
import com.vesta.service.converter.CompanyConverter;
import com.vesta.service.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyConverter companyConverter;

    @Override
    public List<CompanyDto> findAll(){
        return companyRepository.findAll()
                .stream()
                .map(companyConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getByName(String name) {
        return companyConverter.convert(getCompanyEntityByUsername(name,
                new NotFoundException("The username not found")));
    }

    private CompanyEntity getCompanyEntityByUsername(String username, VestaException exception) {
        return companyRepository
                .findByName(username)
                .orElseThrow(() -> exception);
    }
}

