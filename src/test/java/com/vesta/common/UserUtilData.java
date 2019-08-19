package com.vesta.common;

import com.vesta.repository.entity.UserEntity;
import com.vesta.service.dto.AccountCredential;
import com.vesta.service.dto.UserDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Collections;

public class UserUtilData {

    public static String USER_USERNAME = RandomStringUtils.randomAlphabetic(10);
    public static String USER_EMAIL = RandomStringUtils.randomAlphabetic(10) + "@gmail.com";
    public static String USER_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    public static String USER_NEW_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    private static Long USER_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    private static String USER_LAST_NAME = RandomStringUtils.randomAlphabetic(10);
    private static String USER_FIRST_NAME = RandomStringUtils.randomAlphabetic(10);

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

    public static UserDto userDto() {
        UserDto userDto = new UserDto();
        userDto.setId(USER_ID);
        userDto.setUsername(USER_USERNAME);
        userDto.setEmail(USER_EMAIL);
        userDto.setPassword(USER_PASSWORD);
        userDto.setLastName(USER_LAST_NAME);
        userDto.setFirstName(USER_FIRST_NAME);
        userDto.setRoles(Collections.emptyList());

        return userDto;
    }

    public static UserDto userDto(String username) {
        UserDto userDto = userDto();
        userDto.setUsername(username);

        return userDto;
    }

    public static AccountCredential accountCredential() {
        AccountCredential accountCredential = new AccountCredential();
        accountCredential.setUsername(USER_USERNAME);
        accountCredential.setPassword(USER_PASSWORD);

        return accountCredential;
    }
}
