package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserCreateView implements Serializable {

    private static final long serialVersionUID = 98285181758362666L;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
