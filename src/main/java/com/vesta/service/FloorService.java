package com.vesta.service;

import com.vesta.service.dto.FloorDto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface FloorService {

    List<FloorDto> findAll();

    FloorDto getById(@NotNull Long id);

    void create(@Valid FloorDto floorDto);

    void delete(@NotNull Long id);

    FloorDto update (@NotNull Long id, @Valid FloorDto floorDto);
}