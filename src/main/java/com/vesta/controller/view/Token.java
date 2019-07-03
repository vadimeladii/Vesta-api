package com.vesta.controller.view;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Token implements Serializable {

    private static final long serialVersionUID = 3886412712132300473L;

    private String token;
}
