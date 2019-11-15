package com.vesta.controller.convertor;

import com.vesta.controller.view.DeviceCreateView;
import com.vesta.service.dto.DeviceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceCreateViewConverter implements Converter<DeviceDTO, DeviceCreateView> {
    @Override
    public DeviceCreateView convert(DeviceDTO dto) {
        if (dto == null) return null;

        DeviceCreateView view = new DeviceCreateView();
        view.setDeviceName(dto.getDeviceName());
        view.setIpAddress(dto.getIpAddress());
        view.setOperatingSystem(dto.getOperatingSystem());
        view.setProcessor(dto.getProcessor());
        view.setAccessLevel(dto.getAccessLevel());
        view.setIsPortable(dto.getIsPortable());

        return view;
    }

    public DeviceDTO deconvert(DeviceCreateView view) {
        if (view == null) return null;

        DeviceDTO dto = new DeviceDTO();
        dto.setDeviceName(view.getDeviceName());
        dto.setIpAddress(view.getIpAddress());
        dto.setOperatingSystem(view.getOperatingSystem());
        dto.setProcessor(view.getProcessor());
        dto.setAccessLevel(view.getAccessLevel());
        dto.setIsPortable(view.getIsPortable());

        return dto;
    }
}