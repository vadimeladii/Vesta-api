package com.vesta.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDto {

    private Long id;
    private String name;
    private List<FloorDto> floors;
}
