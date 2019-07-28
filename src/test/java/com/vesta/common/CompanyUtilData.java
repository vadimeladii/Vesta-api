package com.vesta.common;

import com.vesta.repository.entity.CompanyEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class CompanyUtilData {

    public static Long COMPANY_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String COMPANY_NAME = RandomStringUtils.randomAlphabetic(10);
    public static Integer COMPANY_FLOOR = Integer.parseInt(RandomStringUtils.randomNumeric(5));


    public static CompanyEntity companyEntity() {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(COMPANY_ID);
        companyEntity.setName(COMPANY_NAME);
        companyEntity.setFloor(COMPANY_FLOOR);

        return companyEntity;
    }

    public static CompanyEntity companyEntity(String name) {
        CompanyEntity companyEntity = companyEntity();
        companyEntity.setName(name);

        return companyEntity;
    }
}
