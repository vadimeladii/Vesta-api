package com.vesta.service;

import com.vesta.service.dto.SubjectImageDto;

import java.util.List;

public interface SubjectImageService {

    SubjectImageDto getById(Long id);

    void createImage(SubjectImageDto subjectImageDto);

    void deleteImage(Long id);

    List<SubjectImageDto> getAll();
}
