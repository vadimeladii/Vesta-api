package com.vesta.controller;

import com.vesta.controller.view.CompanyView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/company")
@Api(value = "Company Controller REST Endpoint")
public interface CompanyController {

    @ApiOperation(value = "Returneaza toate companiile din baza de date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all companies has succeeded"),
            @ApiResponse(code = 404, message = "Company not found")
    })
    @GetMapping
    List<CompanyView> findAll();
}