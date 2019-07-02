package com.vesta.service.dto;

import lombok.Data;

import com.vesta.config.validations.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
public class UserDto {

    private Long id;

    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 25, message = "First name should be more then 2 and less then 25 characters" )
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 2, max = 25, message = "Last name should be more then 2 and less then 25 characters" )
    private String lastName;

    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 25, message = "Username should be more then 5 and less then 25 characters" )
    private String username;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @ValidPassword(message = "Password should be more then 8 and less then 32 characters and" +
            "at least one upper-case character," +
            "at least one lower-case character," +
            "at least one digit character" +
            "at least one symbol (special character)" +
            "no whitespace")
    private String password;

    private List<String> roles;
}