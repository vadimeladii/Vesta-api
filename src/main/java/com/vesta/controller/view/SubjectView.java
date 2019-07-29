package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectView implements Serializable {

    private static final long serialVersionUID = 7217837176434251304L;

    private Long id;
    private List<Float> position;
    private Float scale;
    private Float rotation;
    private Boolean editable;
    private String image;
}
