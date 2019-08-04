package com.vesta.service;

import com.vesta.service.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    SubjectDto getById(Long id);

    List<SubjectDto> getAll();

    void delete(Long id);

    List<SubjectDto> getByFloorId(Long floorId);

    void create(SubjectDto dto);
}