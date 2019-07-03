package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserView implements Serializable {

    private static final long serialVersionUID = -20051318655195135L;

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
