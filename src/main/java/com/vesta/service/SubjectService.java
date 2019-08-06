package com.vesta.service;

import com.vesta.service.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    SubjectDto getById(Long id);

    List<SubjectDto> getAll();

    void delete(Long id);

    void delete(List<Long> ids);
  
    List<SubjectDto> getByFloorId(Long floorId);

    void create(SubjectDto dto);

    void create(List<SubjectDto> dtos);

    SubjectDto update(Long id, SubjectDto dto);
}

