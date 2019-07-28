package com.vesta.controller.impl;

import com.vesta.controller.CompanyController;
import com.vesta.controller.convertor.CompanyViewConverter;
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

    @Override
    public List<CompanyView> findAll() {
        return companyService.findAll()
                .stream()
                .map(companyViewConverter::convert)
                .collect(Collectors.toList());
    }

}
