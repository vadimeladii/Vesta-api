package com.vesta.controller.view;

import lombok.Data;

@Data
public class UserView {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}