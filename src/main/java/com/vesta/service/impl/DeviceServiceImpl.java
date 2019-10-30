package com.vesta.service.impl;

import com.vesta.exception.NotFoundException;
import com.vesta.repository.DeviceRepository;
import com.vesta.repository.entity.DeviceEntity;
import com.vesta.service.DeviceService;
import com.vesta.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;

    @Override
    public DeviceDTO getById(Long id){

        log.info("method --- getByID() for device");

        DeviceEntity deviceEntity = deviceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("There is no such device"));
        return
    }

}
