package com.vesta.common;

import com.vesta.controller.view.CompanyView;
import com.vesta.repository.entity.CompanyEntity;
import com.vesta.repository.entity.FloorEntity;
import com.vesta.service.converter.FloorConverter;
import com.vesta.service.dto.CompanyDto;
import com.vesta.service.dto.FloorDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CompanyUtilData {

    private static Long COMPANY_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String COMPANY_NAME = RandomStringUtils.randomAlphabetic(10);
    private static FloorConverter floorConverter = new FloorConverter();

    public static CompanyDto companyDtoWithoutFloors() {
        CompanyDto companyDto = new CompanyDto();
        companyDto.setId(COMPANY_ID);
        companyDto.setName(COMPANY_NAME);

        return companyDto;
    }

    public static CompanyDto companyDto() {
        CompanyDto companyDto = new CompanyDto();
        List<FloorDto> floorsDto = List
                .of(Objects.requireNonNull(floorConverter.convert(FloorUtilData
                .floorEntity(COMPANY_ID))));

        companyDto.setId(COMPANY_ID);
        companyDto.setName(COMPANY_NAME);
        companyDto.setFloors(floorsDto);

        return companyDto;
    }

    public static CompanyEntity companyEntity() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(COMPANY_ID);
        companyEntity.setName(COMPANY_NAME);
        companyEntity.setFloors(List.of(FloorUtilData.floorEntity()));

        return companyEntity;
    }

    public static CompanyEntity companyEntityWithoutFloors() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(COMPANY_ID);
        companyEntity.setName(COMPANY_NAME);

        return companyEntity;
    }

    public static CompanyEntity companyEntity(String companyName) {
        CompanyEntity companyEntity = companyEntity();
        companyEntity.setName(companyName);

        return companyEntity;
    }

    public static CompanyView companyViewWithoutFloors() {
        CompanyView companyView = new CompanyView();
        companyView.setId(COMPANY_ID);
        companyView.setName(COMPANY_NAME);
        companyView.setFloors(List.of());

        return companyView;
    }
}
