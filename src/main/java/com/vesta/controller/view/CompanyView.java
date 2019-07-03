package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyView implements Serializable {

    private static final long serialVersionUID = -8361339528981156917L;

    private Long id;
    private String name;
    private Integer floor;
}
