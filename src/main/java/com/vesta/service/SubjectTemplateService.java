package com.vesta.service;

import com.vesta.service.dto.SubjectTemplateDto;

import javax.validation.Valid;
import java.util.List;

public interface SubjectTemplateService {

    SubjectTemplateDto getById(Long id);

    void create(SubjectTemplateDto subjectTemplateDto);

    void delete(Long id);

    List<SubjectTemplateDto> getAll();

    SubjectTemplateDto update (Long id, @Valid SubjectTemplateDto subjectTemplateDto);
}