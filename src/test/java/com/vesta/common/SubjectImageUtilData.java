package com.vesta.common;

import com.vesta.repository.entity.SubjectImageEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class SubjectImageUtilData {

    public static Long SUBJECT_IMAGE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String SUBJECT_IMAGE = RandomStringUtils.randomAlphabetic(100);

    public static SubjectImageEntity subjectImageEntity(){
        SubjectImageEntity subjectImageEntity = new SubjectImageEntity();
        subjectImageEntity.setId(SUBJECT_IMAGE_ID);
        subjectImageEntity.setImage(SUBJECT_IMAGE);

        return subjectImageEntity;
    }
}
