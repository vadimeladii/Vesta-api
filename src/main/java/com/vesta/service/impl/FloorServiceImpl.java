package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.FloorRepository;
import com.vesta.repository.entity.FloorEntity;
import com.vesta.service.FloorService;
import com.vesta.service.converter.FloorConverter;
import com.vesta.service.dto.FloorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService {

    private final FloorRepository floorRepository;

    private final FloorConverter floorConverter;

    @Override
    public List<FloorDto> findAll() {
        log.info("method --- findAll");

        return floorRepository.findAll()
                .stream()
                .map(floorConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public FloorDto getById(Long id) {
        log.info("method --- getById");

        return floorConverter.convert(floorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The floor not found")));
    }

    @Override
    public void create(@Valid FloorDto floorDto) {
        log.info("method --- create");

        FloorEntity entity = floorConverter.deconvert(floorDto);
        floorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete");

        floorRepository.deleteById(id);
    }
}