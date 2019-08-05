package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.SubjectTemplateRepository;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.service.SubjectTemplateService;
import com.vesta.service.converter.SubjectTemplateConverter;
import com.vesta.service.dto.SubjectTemplateDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SubjectTemplateServiceImpl implements SubjectTemplateService {

    private final SubjectTemplateRepository subjectTemplateRepository;
    private final SubjectTemplateConverter converter;

    @Override
    public SubjectTemplateDto getById(Long id) {
        log.info("method --- getByID");

        SubjectTemplateEntity subjectTemplateEntity = subjectTemplateRepository.findById(id).orElseThrow(
                () -> new NotFoundException("The Image doesn't exist"));
        return converter.convert(subjectTemplateEntity);
    }

    @Override
    public void create(SubjectTemplateDto subjectTemplateDto) {
        log.info("method --- createImage");

        SubjectTemplateEntity subjectTemplateEntity = converter.deconvert(subjectTemplateDto);
        subjectTemplateRepository.save(subjectTemplateEntity);
    }

    @Override
    public void delete(Long id) {
        log.info("method --- deleteImage");

        subjectTemplateRepository.deleteById(id);
    }

    @Override
    public List<SubjectTemplateDto> getAll() {
        log.info("method --- findAll");

        return subjectTemplateRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
