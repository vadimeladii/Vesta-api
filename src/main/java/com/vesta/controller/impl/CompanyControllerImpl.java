package com.vesta.controller.impl;

import com.vesta.controller.CompanyController;
import com.vesta.controller.convertor.CompanyViewConverter;
import com.vesta.controller.view.CompanyView;
import com.vesta.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CompanyControllerImpl implements CompanyController {

    private final CompanyService companyService;

    private final CompanyViewConverter companyViewConverter;

    @ApiOperation(value = "Returneaza toate companiile din baza de date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all companies has succeeded"),
            @ApiResponse(code = 404, message = "Company not found")
    })
    public List<CompanyView> findAll() {
        return companyService.findAll()
                .stream()
                .map(companyViewConverter::convert)
                .collect(Collectors.toList());
    }

}
