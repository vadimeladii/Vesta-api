package com.vesta.controller.view;

import lombok.Data;
import java.io.Serializable;

@Data
public class TeamView implements Serializable {

    private static final long serialVersionUID = 1007083335317362570L;

    private Long id;
    private String firstName;
    private String lastName;
    private String projectName;
    private String office;
    private String phone;
    private String mobilePhone;
    private String hasOfficeKeys;
    private String workAtPentalogSince;
    private String dateOfBirth;
}