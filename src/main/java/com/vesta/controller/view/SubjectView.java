package com.vesta.controller.view;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.List;

@Data
public class SubjectView implements Serializable {

    private static final long serialVersionUID = 7217837176434251304L;

    @Null
    private Long id;
    @NotNull
    private List<Float> position;
    @NotNull
    private Float scale;
    @NotNull
    private Float rotation;
    @NotNull
    private Boolean editable;
    @NotNull
    private Long floorId;
    @NotNull
    private Object utilities;
    @NotNull
    private String image;
}
