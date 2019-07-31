package com.vesta.common;

import com.vesta.repository.entity.SubjectTemplateEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class SubjectTemplateUtilData {

    public static Long SUBJECT_IMAGE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String SUBJECT_IMAGE = RandomStringUtils.randomAlphabetic(100);

    public static SubjectTemplateEntity subjectTemplateEntity(){
        SubjectTemplateEntity subjectImageEntity = new SubjectTemplateEntity();
        subjectImageEntity.setId(SUBJECT_IMAGE_ID);
        subjectImageEntity.setImage(SUBJECT_IMAGE);

        return subjectImageEntity;
    }
}
