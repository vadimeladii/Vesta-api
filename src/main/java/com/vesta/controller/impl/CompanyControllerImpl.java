package com.vesta.controller.impl;

import com.vesta.controller.CompanyController;
import com.vesta.controller.convertor.CompanyUpdateViewConverter;
import com.vesta.controller.convertor.CompanyViewConverter;
import com.vesta.controller.view.CompanyUpdateView;
import com.vesta.controller.view.CompanyView;
import com.vesta.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;
    private final CompanyViewConverter companyViewConverter;
    private final CompanyUpdateViewConverter companyUpdateViewConverter;

    @Override
    public List<CompanyView> findAll() {
        return companyService.findAll()
                .stream()
                .map(companyViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyView findById(Long id) {
        return companyViewConverter.convert(companyService.findById(id));
    }

    @Override
    public void create(CompanyView companyView) {
        companyService.create(companyViewConverter.deconvert(companyView));
    }

    @Override
    public void delete(Long id) {
        companyService.delete(id);
    }

    @Override
    public CompanyView findByName(String name) {
        return companyViewConverter.convert((companyService.getByName(name)));
    }

    @Override
    public CompanyView update(Long id, CompanyUpdateView companyView){
        return companyViewConverter.convert(companyService.update(id, companyUpdateViewConverter.deconvert(companyView)));
    }
}