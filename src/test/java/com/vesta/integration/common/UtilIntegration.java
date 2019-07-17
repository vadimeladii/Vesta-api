package com.vesta.integration.common;

import com.vesta.repository.entity.CompanyEntity;
import com.vesta.repository.entity.UserEntity;

public class UtilIntegration {

    public static UserEntity createUserEntiy() {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setUsername("username");
        userEntity.setEmail("vesta.api@gmail.com");
        userEntity.setPassword("password");
        userEntity.setLastName("ForTest");
        userEntity.setFirstName("ForTest1");

        return userEntity;
    }

    public static UserEntity createUserEntiyWithPassword(String password) {

        UserEntity userEntity = createUserEntiy();
        userEntity.setPassword(password);

        return userEntity;
    }

    public static CompanyEntity createCompayEntity() {

        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(1L);
        companyEntity.setName("test");
        companyEntity.setFloor(1);

        return companyEntity;
    }
}
