package com.vesta.service.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TeamDto {

    private Long id;

    @NotEmpty(message = "First name is required")
    @Size(min = 2, max = 25, message = "First name should be more then 2 and less then 25 characters")
    private String firstName;

    @NotEmpty(message = "Last Name ie required")
    @Size(min = 2, max = 25, message = "Last name should be more then 2 and less then 25 characters")
    private String lastName;

    @NotEmpty(message = "Project and project role are required")
    @Size(min = 2, max = 50, message = "Project and project role should be more then 2 and less then 50 characters")
    private String projectName;

    @NotEmpty(message = "Office is required")
    @Size(min = 3, max = 10, message = "Office should be more then 3 and less then 10 characters")
    private String office;

    @NotEmpty(message = "Phone number is required")
    @Size(min = 8, max = 20,message = "Phone should be more then 8 and less then 20 characters")
    private String phone;

    @NotEmpty(message = "Mobile phone number is required")
    @Size(min = 8, max = 20,message = "Mobile phone should be more then 8 and less then 20 characters")
    private String mobilePhone;

    @NotEmpty(message = "Office key is required")
    @Size(min =1, max = 4, message = "Key should be more then 1 and less then 4 characters")
    private String hasOfficeKeys;

    @NotEmpty(message = "Date is required")
    @Size(max = 11, message = "Date should be less then 11 characters")
    private String workAtPentalogSince;

    @NotEmpty(message = "Date of birth is required")
    @Size(max = 11, message = "Date of birth should be less then 11 characters")
    private String dateOfBirth;

}
