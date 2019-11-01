package com.vesta.controller.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceView implements Serializable {

    private String deviceName;
    private String operatingSystem;
    private String ipAddress;
    private String processor;
    private Integer accessLevel;
    private Boolean isPortable;
}
