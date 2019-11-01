package com.vesta.controller.view;

import lombok.Data;
import java.io.Serializable;

@Data
public class TeamCreateView implements Serializable {

    private static final long serialVersionUID = 1824070080734246776L;

    private String firstName;
    private String lastName;
    private String projectName;
    private String office;
    private String phone;
    private String mobilePhone;
    private String  hasOfficeKeys;
    private String workAtPentalogSince;
    private String dateOfBirth;
}
