package com.vesta.common;

import com.vesta.repository.entity.CompanyEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompanyUtilData {

    private static Long COMPANY_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String COMPANY_NAME = RandomStringUtils.randomAlphabetic(10);


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

    public static CompanyEntity companyEntity(String name) {
        CompanyEntity companyEntity = companyEntity();
        companyEntity.setName(name);

        return companyEntity;
    }
}
