package com.vesta.service;

import com.vesta.service.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    void addSubject(SubjectDto dto);

    SubjectDto updateSubject(Long id, SubjectDto dto);

    SubjectDto getById(Long id);

    List<SubjectDto> getAll();

    void delete(Long id);
}
