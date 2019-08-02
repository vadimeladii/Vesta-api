package com.vesta.common;

import com.vesta.repository.entity.FloorEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class FloorUtilData {

    private static Long FLOOR_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    private static String FLOOR_NAME = RandomStringUtils.randomAlphabetic(10);
    private static Long FLOOR_COMPANY_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));

    public static FloorEntity floorEntity(){
        FloorEntity floorEntity = new FloorEntity();
        floorEntity.setId(FLOOR_ID);
        floorEntity.setName(FLOOR_NAME);
        floorEntity.setCompanyId(FLOOR_COMPANY_ID);

        return floorEntity;
    }

    public static FloorEntity floorEntity(Long companyId){
        FloorEntity floorEntity = floorEntity();
        floorEntity.setCompanyId(companyId);

        return floorEntity;
    }
}
