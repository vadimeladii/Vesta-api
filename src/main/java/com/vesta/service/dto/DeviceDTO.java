package com.vesta.service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class DeviceDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Name of device is required")
    @Size(max = 50, message = "Device name should be less than 50 characters")
    private String deviceName;

    @NotEmpty(message = "Operating system is required")
    @Size(max = 50, message = "Name of operating system should be less than 50 characters")
    private String operatingSystem;

    @NotEmpty(message = "IP address is required")
    @Size(min = 8, max = 15, message = "IP address can't be less than 8 characters and bigger than 15 characters")
    private String ipAddress;

    private Integer accessLevel;

    @NotEmpty(message = "Name of processor is required")
    @Size(max = 50, message = "Processor name should be less than 50 characters")
    private String processor;

    @NotNull
    private boolean isPortable;
}
