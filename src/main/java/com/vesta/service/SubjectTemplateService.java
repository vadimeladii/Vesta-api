package com.vesta.service;

import com.vesta.service.dto.SubjectTemplateDto;

import java.util.List;

public interface SubjectTemplateService {

    SubjectTemplateDto getById(Long id);

    void create(SubjectTemplateDto subjectTemplateDto);

    void delete(Long id);

    List<SubjectTemplateDto> getAll();
}
