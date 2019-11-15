package com.vesta.controller.convertor;

import com.vesta.controller.view.DeviceView;
import com.vesta.service.dto.DeviceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceViewConverter implements Converter<DeviceDTO, DeviceView> {
    @Override
    public DeviceView convert(DeviceDTO dto) {
        if (dto == null) return null;

        DeviceView view = new DeviceView();
        view.setId(dto.getId());
        view.setDeviceName(dto.getDeviceName());
        view.setIpAddress(dto.getIpAddress());
        view.setOperatingSystem(dto.getOperatingSystem());
        view.setProcessor(dto.getProcessor());
        view.setAccessLevel(dto.getAccessLevel());
        view.setIsPortable(dto.getIsPortable());

        return view;
    }
}