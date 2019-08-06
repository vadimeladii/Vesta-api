package com.vesta.common;

import com.vesta.controller.view.SubjectView;
import com.vesta.repository.entity.SubjectEntity;
import com.vesta.service.dto.SubjectDto;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;

public class SubjectUtilData {

    public static Long SUBJECT_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_POSITION_X = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_POSITION_Y = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_SCALE = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Float SUBJECT_ROTATION = Float.parseFloat(RandomStringUtils.randomNumeric(10));
    public static Boolean SUBJECT_EDITABLE = Boolean.parseBoolean(RandomStringUtils.random(10));
    public static Long FLOOR_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String TEMPLATE = "/img/svg/table.svg";

    public static SubjectEntity subjectEntity() {
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

    public static SubjectView subjectView() {

        SubjectView subjectView = new SubjectView();

        subjectView.setPosition(List.of(SUBJECT_POSITION_X, SUBJECT_POSITION_Y));
        subjectView.setScale(SUBJECT_SCALE);
        subjectView.setRotation(SUBJECT_ROTATION);
        subjectView.setEditable(SUBJECT_EDITABLE);
        subjectView.setFloorId(FLOOR_ID);
        subjectView.setImage(TEMPLATE);

        return subjectView;
    }

    public static SubjectDto subjectDto() {

        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setPositionX(SUBJECT_POSITION_X);
        subjectDto.setPositionY(SUBJECT_POSITION_Y);
        subjectDto.setScale(SUBJECT_SCALE);
        subjectDto.setRotation(SUBJECT_ROTATION);

        return subjectDto;
    }
}
