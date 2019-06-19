
package com.vesta.controller.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserView {

    @ApiModelProperty(notes = "id of the user")
    private Long id;
    @ApiModelProperty(notes = "first name of the user")
    private String firstName;
    @ApiModelProperty(notes = "last name of the user")
    private String lastName;
    @ApiModelProperty(notes = "username")
    private String username;
    @ApiModelProperty(notes = "email of the user")
    private String email;
    @ApiModelProperty(notes = "password of the user")
    private String password;
    @ApiModelProperty(notes = "role of the user")
    private List<String> roles;
}
