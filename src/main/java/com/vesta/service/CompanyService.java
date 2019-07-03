package com.vesta.service;

import com.vesta.service.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<CompanyDto> findAll();

    CompanyDto getByName(String name);
}
