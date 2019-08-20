package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectUpdateView implements Serializable {

    private static final long serialVersionUID = -4306283401774818625L;

    private List<Float> position;
    private Float scale;
    private Float rotation;
    private String additional;

}


