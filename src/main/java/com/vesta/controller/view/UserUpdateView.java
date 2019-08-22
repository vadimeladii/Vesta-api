package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateView implements Serializable {

    private static final long serialVersionUID = 3124621392305146272L;

    private String firstName;
    private String lastName;
    private String email;
}
