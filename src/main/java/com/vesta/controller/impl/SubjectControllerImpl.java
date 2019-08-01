package com.vesta.controller.impl;

import com.vesta.controller.SubjectController;
import com.vesta.controller.convertor.SubjectViewConverter;
import com.vesta.controller.view.SubjectView;
import com.vesta.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class SubjectControllerImpl implements SubjectController {

    private final SubjectService service;
    private final SubjectViewConverter converter;

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public SubjectView getById(Long id) {
        return converter.convert(service.getById(id));
    }

    @Override
    public List<SubjectView> getAll() {
        return service.getAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<SubjectView> getAllByFloorId(Long floorId) {
        return service.getAllByFloorId(floorId)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(SubjectView subjectView) {
        service.create(converter.deconvert(subjectView));
    }
}
