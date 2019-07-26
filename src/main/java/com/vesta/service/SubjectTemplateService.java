package com.vesta.service;

import com.vesta.service.dto.SubjectTemplateDto;

import java.util.List;

public interface SubjectTemplateService {

    SubjectTemplateDto getById(Long id);

    void createImage(SubjectTemplateDto subjectTemplateDto);

    void deleteImage(Long id);

    List<SubjectTemplateDto> getAll();
}
