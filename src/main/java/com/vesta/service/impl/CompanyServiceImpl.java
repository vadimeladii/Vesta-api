package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.CompanyRepository;
import com.vesta.service.CompanyService;
import com.vesta.service.converter.CompanyConverter;
import com.vesta.service.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyConverter companyConverter;

    @Override
    public List<CompanyDto> findAll() {
        log.info("method --- findAll");

        return companyRepository.findAll()
                .stream()
                .map(companyConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyDto getByName(String name) {
        log.info("method --- getByName");

        return companyConverter.convert(companyRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("The username not found")));
    }
}

