package com.vesta.common;

import com.vesta.repository.entity.FloorEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class FloorUtilData {

    public static Long FLOOR_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String FLOOR_NAME = RandomStringUtils.randomAlphabetic(10);

    public static FloorEntity floorEntity(){
        FloorEntity floorEntity = new FloorEntity();
        floorEntity.setId(FLOOR_ID);
        floorEntity.setName(FLOOR_NAME);

        return floorEntity;
    }
}
