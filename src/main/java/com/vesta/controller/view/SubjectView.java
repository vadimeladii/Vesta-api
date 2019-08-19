package com.vesta.controller.view;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class SubjectView implements Serializable {

    private static final long serialVersionUID = 5316459103183268359L;

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
    private String image;
    @NotNull
    private Object additional;
}
