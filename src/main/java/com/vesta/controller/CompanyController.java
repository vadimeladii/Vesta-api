package com.vesta.controller;

import com.vesta.controller.view.CompanyView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company")
@Api(value = "Company Controller REST Endpoint")
public interface CompanyController {

    @ApiOperation(value = "Return all companies")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all companies has succeeded"),
            @ApiResponse(code = 404, message = "Company not found")
    })
    @GetMapping
    List<CompanyView> findAll();

    @ApiOperation(value = "Return the company by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get company by id has succeeded"),
            @ApiResponse(code = 404, message = "Company not found")
    })
    @GetMapping("/{id}")
    CompanyView findById(@PathVariable("id") Long id);

    @ApiOperation(value = "Create a new company")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Company was created"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void create(@RequestBody CompanyView companyView);

    @ApiOperation(value = "Delete company by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Company was deleted successfully"),
    })
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Long id);
}