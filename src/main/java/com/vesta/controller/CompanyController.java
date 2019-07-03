package com.vesta.controller;

import com.vesta.controller.view.CompanyView;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/company")
@Api(value = "Company Controller REST Endpoint")
public interface CompanyController {

    @GetMapping
    List<CompanyView> findAll();
}