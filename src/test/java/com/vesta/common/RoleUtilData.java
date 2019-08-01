package com.vesta.common;

import com.vesta.repository.entity.RoleEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class RoleUtilData {

    private static Long ROLE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    private static String ROLE_NAME = RandomStringUtils.randomAlphabetic(10);

    public static RoleEntity roleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(ROLE_ID);
        roleEntity.setName(ROLE_NAME);

        return roleEntity;
    }
}
