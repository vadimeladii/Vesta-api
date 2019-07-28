package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class FloorView implements Serializable {

    private static final long serialVersionUID = -3371255701735930403L;

    private Long id;
    private String name;
}
