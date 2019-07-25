package com.vesta.controller.impl;

import com.vesta.controller.FloorController;
import com.vesta.controller.convertor.FloorViewConverter;
import com.vesta.controller.view.FloorView;
import com.vesta.service.FloorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class FloorControllerImpl implements FloorController {

    private final FloorService floorService;

    private final FloorViewConverter floorViewConverter;

    @Override
    public List<FloorView> findAll() {
        return floorService.findAll()
                .stream()
                .map(floorViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public FloorView getById(Long id) {
        return floorViewConverter.convert(floorService.getById(id));
    }

    @Override
    public void create(FloorView floorView) {
        floorService.create(floorViewConverter.deconvert(floorView));
    }

    @Override
    public void delete(Long id) {
        floorService.delete(id);
    }

}