package com.vesta.service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = 1537838341726274065L;
    private Long id;

    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 25, message = "First name should be more then 2 and less then 25 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 25, message = "Last name should be more then 2 and less then 25 characters")
    private String lastName;

    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 25, message = "Username should be more then 5 and less then 25 characters")
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 6, max = 32, message = "Password should be more then 6 and less then 32 characters")
    private String password;

    private List<String> roles;
}