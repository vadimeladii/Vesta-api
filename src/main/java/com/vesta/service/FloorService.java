package com.vesta.service;

import com.vesta.service.dto.FloorDto;

import java.util.List;

public interface FloorService {

    List<FloorDto> findAll();

    FloorDto getById(Long id);
}