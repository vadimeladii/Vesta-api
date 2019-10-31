package com.vesta.service.impl;

import com.vesta.exception.ConflictException;
import com.vesta.exception.NotFoundException;
import com.vesta.repository.DeviceRepository;
import com.vesta.repository.entity.DeviceEntity;
import com.vesta.service.DeviceService;
import com.vesta.service.converter.DeviceConverter;
import com.vesta.service.dto.DeviceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceConverter deviceConverter;

    @Override
    public DeviceDTO getById(Long id){

        log.info("method --- getByID() for device");

        DeviceEntity deviceEntity = deviceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("There is no such device"));
        return deviceConverter.convert(deviceEntity);
    }

    @Override
    public List<DeviceDTO> findAll(){
        log.info("method --- findAll() for device");

        return deviceRepository.findAll().stream().map(deviceConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void create(DeviceDTO deviceDTO){
        log.info("method --- create() for device");

        verify(deviceRepository.existsByDeviceName(deviceDTO.getDevice_name()),
                () -> new ConflictException("There is already such device name in system"));
        verify(deviceRepository.existsByIpAddress(deviceDTO.getIp_address()),
                () -> new ConflictException("There is device with the same IP address in system"));

        DeviceEntity dEntity = deviceConverter.deconvert(deviceDTO);
        deviceRepository.save(dEntity);
    }

    @Override
    public DeviceDTO update(Long id, DeviceDTO deviceDTO){
        log.info("method --- update() for device");

        DeviceEntity deviceEntity = deviceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("There is no device with such ID in system"));

        DeviceEntity editedDevice = deviceConverter.deconvert(deviceDTO);
        deviceEntity.setDevice_name(editedDevice.getDevice_name());
        deviceEntity.setProcessor(editedDevice.getProcessor());
        deviceEntity.setIp_address(editedDevice.getIp_address());
        deviceEntity.setIs_portable(editedDevice.getIs_portable());
        deviceEntity.setAccess_level(editedDevice.getAccess_level());
        deviceEntity.setOperating_system(editedDevice.getOperating_system());

        return deviceConverter.convert(deviceRepository.save(deviceEntity));
    }

    @Override
    public DeviceDTO getByDeviceName(String device_name){
        log.info("method --- getByDeviceName() for device");

        return deviceConverter.convert(deviceRepository.findByDeviceName(device_name).orElseThrow( () ->
                new NotFoundException("There is no such device in system")));
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete() for device");

        deviceRepository.deleteById(id);
    }
}
