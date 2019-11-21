package com.vesta.service;

import com.vesta.service.dto.CompanyDto;
import javax.validation.Valid;
import java.util.List;

public interface CompanyService {
    List<CompanyDto> findAll();

    CompanyDto getByName(String name);

    void create(CompanyDto dto);

    CompanyDto update(Long id, @Valid CompanyDto companyDto);

    CompanyDto findById(Long id);

    void delete(Long id);
}