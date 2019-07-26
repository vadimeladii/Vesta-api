package com.vesta.common;

import com.vesta.repository.entity.CompanyEntity;
import com.vesta.repository.entity.RoleEntity;
import com.vesta.repository.entity.SubjectTemplateEntity;
import com.vesta.repository.entity.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;

public class UtilData {

    private static Long USER_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String USER_USERNAME = RandomStringUtils.randomAlphabetic(10);
    public static String USER_EMAIL = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    public static String USER_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    public static String USER_NEW_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    private static String USER_LAST_NAME = RandomStringUtils.randomAlphabetic(10);
    private static String USER_FIRST_NAME = RandomStringUtils.randomAlphabetic(10);

    public static Long SUBJECT_IMAGE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String SUBJECT_IMAGE = RandomStringUtils.randomAlphabetic(100);

    private static Long COMPANY_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String COMPANY_NAME = RandomStringUtils.randomAlphabetic(10);
    private static Integer COMPANY_FLOOR = Integer.parseInt(RandomStringUtils.randomNumeric(5));

    private static Long ROLE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    private static String ROLE_NAME = RandomStringUtils.randomAlphabetic(10);

    public static UserEntity userEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(USER_ID);
        userEntity.setUsername(USER_USERNAME);
        userEntity.setEmail(USER_EMAIL);
        userEntity.setPassword(USER_PASSWORD);
        userEntity.setLastName(USER_LAST_NAME);
        userEntity.setFirstName(USER_FIRST_NAME);
        userEntity.setRoles(Collections.emptyList());

        return userEntity;
    }

    public static UserEntity userEntity(String password) {
        UserEntity userEntity = userEntity();
        userEntity.setPassword(password);

        return userEntity;
    }

    public static SubjectTemplateEntity subjectImageEntity(){
        SubjectTemplateEntity subjectTemplateEntity = new SubjectTemplateEntity();
        subjectTemplateEntity.setId(SUBJECT_IMAGE_ID);
        subjectTemplateEntity.setImage(SUBJECT_IMAGE);

        return subjectTemplateEntity;
    }

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

    public static RoleEntity roleEntity() {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(ROLE_ID);
        roleEntity.setName(ROLE_NAME);

        return roleEntity;
    }
}
