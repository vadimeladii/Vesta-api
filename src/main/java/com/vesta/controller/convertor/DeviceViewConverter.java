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
        view.setDeviceName(dto.getDeviceName());
        view.setIpAddress(dto.getIpAddress());
        view.setOperatingSystem(dto.getOperatingSystem());
        view.setProcessor(dto.getProcessor());
        view.setAccessLevel(dto.getAccessLevel());
        view.setIsPortable(dto.isPortable());

        return view;
    }

    public DeviceDTO deconvert(DeviceView view) {
        if(view == null) return null;
        DeviceDTO dto = new DeviceDTO();
        dto.setDeviceName(view.getDeviceName());
        dto.setIpAddress(view.getIpAddress());
        dto.setOperatingSystem(view.getOperatingSystem());
        dto.setProcessor(view.getProcessor());
        dto.setAccessLevel(view.getAccessLevel());
        dto.setPortable(view.getIsPortable());

        return dto;
    }
}
