package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class FloorView implements Serializable {

    private static final long serialVersionUID = 1869169124669723044L;

    private Long id;
    private String name;
    private Long companyId;
}
