package com.vesta.service.dto;

import lombok.Data;

@Data
public class SubjectDto {

    private Long id;
    private String subjectName;
    private Integer positionX;
    private Integer positionY;
    private Float scale;
    private Boolean editable;
    private Integer rotation;
    private Long floorId;
}
