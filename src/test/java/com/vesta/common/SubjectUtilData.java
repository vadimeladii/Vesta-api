package com.vesta.common;

import com.vesta.repository.entity.SubjectEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class SubjectUtilData {

    public static Long SUBJECT_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_POSITION_X = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_POSITION_Y = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_SCALE = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_ROTATION = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Boolean SUBJECT_EDITABLE = Boolean.parseBoolean(RandomStringUtils.random(10));
    public static Long FLOOR_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String TEMPLATE = "/img/svg/table.svg";

    public static SubjectEntity subjectEntity(){
        SubjectEntity entity = new SubjectEntity();
        entity.setId(SUBJECT_ID);
        entity.setPositionX(SUBJECT_POSITION_X);
        entity.setPositionY(SUBJECT_POSITION_Y);
        entity.setScale(SUBJECT_SCALE);
        entity.setRotation(SUBJECT_ROTATION);
        entity.setEditable(SUBJECT_EDITABLE);
        entity.setFloorId(FLOOR_ID);
        entity.setSubjectTemplateEntity(SubjectTemplateUtilData.subjectTemplateEntity());

        return entity;
    }
}