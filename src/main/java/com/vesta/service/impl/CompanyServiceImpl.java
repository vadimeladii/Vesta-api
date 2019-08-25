package com.vesta.service.impl;

import com.vesta.exception.ConflictException;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.CompanyRepository;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.repository.entity.FloorEntity;
import com.vesta.service.CompanyService;
import com.vesta.service.converter.CompanyConverter;
import com.vesta.service.converter.FloorConverter;
import com.vesta.service.dto.CompanyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyConverter companyConverter;
    private final FloorConverter floorConverter;
    private final FloorRepository floorRepository;

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

    @Override
    @Transactional
    public void create(CompanyDto companyDto) {
        log.info("method --- create");

        verify(companyRepository.existsByName(companyDto.getName()),
                () -> new ConflictException("Name already exists"));

        CompanyEntity dbCompany = companyRepository.save(companyConverter.deconvert(companyDto));

        List<FloorEntity> floorEntities = companyDto
                .getFloors()
                .stream()
                .peek(dto -> dto.setCompanyId(dbCompany.getId()))
                .map(floorConverter::deconvert)
                .collect(Collectors.toList());
        floorRepository.saveAll(floorEntities);
    }

    @Override
    public CompanyDto findById(Long id) {

        return companyRepository
                .findById(id)
                .map(companyConverter::convert)
                .orElseThrow(
                        () -> new NotFoundException("The company not found"));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CompanyEntity companyEntity = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("The company not found"));
        List<Long> ids = companyEntity.getFloors().stream().map(FloorEntity::getId).collect(Collectors.toList());
        floorRepository.deleteByIds(ids);
        companyRepository.deleteById(companyEntity.getId());
    }
}

