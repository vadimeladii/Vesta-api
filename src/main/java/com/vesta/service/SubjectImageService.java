package com.vesta.service;

import com.vesta.service.dto.SubjectImageDto;

public interface SubjectImageService {

    SubjectImageDto getById(Long id);

    void createImage(SubjectImageDto subjectImageDto);

    void deleteImage(Long id);
}
