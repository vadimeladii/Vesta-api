package com.vesta.service;

import com.vesta.service.dto.CompanyDto;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> findAll();

    CompanyDto getByName(String name);

    void create(CompanyDto dto);

    CompanyDto findById(Long id);

    void delete(Long id);
}
