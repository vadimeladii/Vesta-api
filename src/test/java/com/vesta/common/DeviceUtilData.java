package com.vesta.common;

import com.vesta.repository.entity.DeviceEntity;
import com.vesta.service.dto.DeviceDTO;
import org.apache.commons.lang3.RandomStringUtils;
import java.util.Random;

//class for making tests in project for Device class
public class DeviceUtilData {
    private static Random random = new Random();
    private static Long DEVICE_ID = Long.parseLong(RandomStringUtils.randomNumeric(10));
    public static String DEVICE_DEVICENAME = RandomStringUtils.randomAlphabetic(30);
    public static String DEVICE_OPERATINGSYSTEM = RandomStringUtils.randomAlphabetic(20);
    public static String DEVICE_IPADDRESS = RandomStringUtils.randomNumeric(3) + "." +
                                            RandomStringUtils.randomNumeric(3) + "." +
                                            RandomStringUtils.randomNumeric(3) + "." +
                                            RandomStringUtils.randomNumeric(3);
    public static String DEVICE_PROCESSOR = RandomStringUtils.randomAlphabetic(20);
    public static Integer DEVICE_ACCESSLEVEL = Integer.parseInt(RandomStringUtils.randomNumeric(2));
    public static Boolean DEVICE_ISPORTABLE = random.nextBoolean();

    //setting all standard classes to test how it all works.
    public static DeviceEntity deviceEntity(){
        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(DEVICE_ID);
        deviceEntity.setDeviceName(DEVICE_DEVICENAME);
        deviceEntity.setOperatingSystem(DEVICE_OPERATINGSYSTEM);
        deviceEntity.setIpAddress(DEVICE_IPADDRESS);
        deviceEntity.setProcessor(DEVICE_PROCESSOR);
        deviceEntity.setAccessLevel(DEVICE_ACCESSLEVEL);
        deviceEntity.setIsPortable(DEVICE_ISPORTABLE);

        return deviceEntity;
    }

    public static DeviceDTO deviceDTO(){
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(DEVICE_ID);
        deviceDTO.setDeviceName(DEVICE_DEVICENAME);
        deviceDTO.setOperatingSystem(DEVICE_OPERATINGSYSTEM);
        deviceDTO.setIpAddress(DEVICE_IPADDRESS);
        deviceDTO.setProcessor(DEVICE_PROCESSOR);
        deviceDTO.setAccessLevel(DEVICE_ACCESSLEVEL);
        deviceDTO.setPortable(DEVICE_ISPORTABLE);

        return deviceDTO;
    }

    public static DeviceDTO deviceDTO(String deviceName){
        DeviceDTO deviceDTO = deviceDTO();
        deviceDTO.setDeviceName(deviceName);

        return deviceDTO;
    }
}