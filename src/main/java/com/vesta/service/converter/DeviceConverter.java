package com.vesta.service.converter;

import com.vesta.repository.entity.DeviceEntity;
import com.vesta.service.dto.DeviceDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceConverter implements Converter<DeviceEntity, DeviceDTO> {

    @Override
    public DeviceDTO convert(DeviceEntity dEntity) {
        if(dEntity == null) return null;
        DeviceDTO dto = new DeviceDTO();
        dto.setDeviceName(dEntity.getDeviceName());
        dto.setOperatingSystem(dEntity.getOperatingSystem());
        dto.setIpAddress(dEntity.getIpAddress());
        dto.setAccessLevel(dEntity.getAccessLevel());
        dto.setProcessor(dEntity.getProcessor());
        dto.setPortable(dEntity.getIsPortable());

        return dto;
    }

    public DeviceEntity deconvert(DeviceDTO dto) {
        if (dto == null) return null;
        DeviceEntity dEntity = new DeviceEntity();
        dEntity.setDeviceName(dto.getDeviceName());
        dEntity.setOperatingSystem(dto.getOperatingSystem());
        dEntity.setIpAddress(dto.getIpAddress());
        dEntity.setAccessLevel(dto.getAccessLevel());
        dEntity.setProcessor(dto.getProcessor());
        dEntity.setIsPortable(dto.isPortable());

        return dEntity;
    }
}
