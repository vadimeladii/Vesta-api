package com.vesta.common;

import com.vesta.repository.entity.AvatarEntity;
import com.vesta.repository.entity.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

public class AvatarUtilData {

    public static Long AVATAR_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String AVATAR_NAME = RandomStringUtils.randomAlphabetic(10);
    private static byte[] AVATAR_BYTE = RandomUtils.nextBytes(20);
    private static UserEntity USER_ID = new UserEntity();

    public static AvatarEntity avatarEntity() {
        AvatarEntity entity = new AvatarEntity();
        entity.setId(AVATAR_ID);
        entity.setName(AVATAR_NAME);
        entity.setAvatar(AVATAR_BYTE);
        entity.setUserEntity(USER_ID);

        return entity;
    }
}
