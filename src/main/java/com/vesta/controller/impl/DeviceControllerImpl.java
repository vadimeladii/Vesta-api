package com.vesta.controller.impl;

import com.vesta.controller.DeviceController;
import com.vesta.controller.convertor.DeviceViewConverter;
import com.vesta.controller.view.DeviceView;
import com.vesta.service.DeviceService;
import com.vesta.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DeviceControllerImpl implements DeviceController {

    private final DeviceService deviceService;
    private final DeviceViewConverter deviceViewConverter;

    @Override
    public DeviceView getById(Long id) { return deviceViewConverter.convert(deviceService.getById(id)); }

    @Override
    public List<DeviceView> findAll(){
        return deviceService.findAll()
                .stream()
                .map(deviceViewConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public void create(DeviceView deviceView) {
        deviceService.create(deviceViewConverter.deconvert(deviceView));
    }

    @Override
    public void delete(Long id) {
        deviceService.delete(id);
    }

    @Override
    public DeviceView update(Long id, DeviceView deviceView) {
        return deviceViewConverter.convert(deviceService.update(id, deviceViewConverter.deconvert(deviceView)));
    }
}
