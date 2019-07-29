package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CompanyView implements Serializable {

    private static final long serialVersionUID = -5440952051091845682L;

    private Long id;
    private String name;
    private List<FloorView> floors;
}
