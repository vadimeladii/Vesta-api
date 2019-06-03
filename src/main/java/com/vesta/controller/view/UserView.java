
package com.vesta.controller.view;

import lombok.Data;

import java.util.List;

@Data
public class UserView {
    private String username;
    private String email;
    private String password;
    private List<String> roles;
}