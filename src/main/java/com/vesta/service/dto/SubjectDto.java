package com.vesta.service.dto;

import com.vesta.repository.entity.SubjectTemplateEntity;
import lombok.Data;

@Data
public class SubjectDto {

    private Long id;
    private Float positionX;
    private Float positionY;
    private Float scale;
    private Boolean editable;
    private Float rotation;
    private Long floorId;
    private String image;
}
