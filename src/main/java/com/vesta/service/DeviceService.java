package com.vesta.service;

import com.vesta.service.dto.DeviceDTO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface DeviceService {
    DeviceDTO getById(Long id);

    DeviceDTO getByDeviceName(String device_name);

    List<DeviceDTO> findAll();

    void create(@Valid DeviceDTO deviceDTO);

    DeviceDTO update(Long id, @Valid DeviceDTO deviceDTO);

    void delete(Long id);
}
