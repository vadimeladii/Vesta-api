
package com.vesta.controler.view;

import lombok.Data;


@Data
public class UserView {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;


}