package com.vesta.service;

import com.vesta.service.dto.FloorDto;

import javax.validation.Valid;
import java.util.List;

public interface FloorService {

    List<FloorDto> findAll();

    FloorDto getById(Long id);

    void create(@Valid FloorDto floorDto);

    void delete(Long id);
}