package com.vesta.controller.impl;

import com.vesta.controller.DeviceController;
import com.vesta.controller.convertor.DeviceCreateViewConverter;
import com.vesta.controller.convertor.DeviceViewConverter;
import com.vesta.controller.view.DeviceCreateView;
import com.vesta.controller.view.DeviceView;
import com.vesta.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeviceControllerImpl implements DeviceController {
    private final DeviceService deviceService;
    private final DeviceViewConverter deviceViewConverter;
    private final DeviceCreateViewConverter deviceCreateViewConverter;

    @Override
    public DeviceView getById(Long id) {
        return deviceViewConverter.convert(deviceService.getById(id));
    }

    @Override
    public DeviceView getByDeviceName(String deviceName) {
        return deviceViewConverter.convert(deviceService.getByDeviceName(deviceName));
    }

    @Override
    public DeviceView getByIpAddress(String ipAddress) {
        return deviceViewConverter.convert(deviceService.getByIpAddress(ipAddress));
    }

    @Override
    public List<DeviceView> findAll(){
        return deviceService.findAll()
                .stream()
                .map(deviceViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(DeviceCreateView deviceCreateView) {
        deviceService.create(deviceCreateViewConverter.deconvert(deviceCreateView));
    }

    @Override
    public DeviceCreateView update(Long id, DeviceCreateView deviceCreateView) {
        return deviceCreateViewConverter.convert(deviceService
                                        .update(id, deviceCreateViewConverter
                                        .deconvert(deviceCreateView)));
    }

    @Override
    public void delete(Long id) {
        deviceService.delete(id);
    }

    @Override
    public void deleteByDeviceName(String deviceName) { deviceService.deleteByDeviceName(deviceName); }

    @Override
    public void deleteByIpAddress(String ipAddress) { deviceService.deleteByIpAddress(ipAddress);}
}