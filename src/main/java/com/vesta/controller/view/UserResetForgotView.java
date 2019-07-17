package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResetForgotView implements Serializable {

    private static final long serialVersionUID = -8873018431575535830L;

    private String token;
    private String password;

}
