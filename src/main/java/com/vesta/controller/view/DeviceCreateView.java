package com.vesta.controller.view;

import lombok.Data;
import java.io.Serializable;

@Data
public class DeviceCreateView implements Serializable {
    private String deviceName;
    private String ipAddress;
    private String processor;
    private String operatingSystem;
    private Boolean isPortable;
    private Integer accessLevel;
}