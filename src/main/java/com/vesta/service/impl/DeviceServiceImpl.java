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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.vesta.expression.ExpressionAsserts.verify;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceConverter deviceConverter;

    @Override
    public DeviceDTO getById(Long id){
        log.info("method --- getByID() for device");
        return deviceConverter.convert(deviceRepository.findById(id).orElseThrow(() ->
                new NotFoundException("There is no device with such ID")));
    }

    @Override
    public DeviceDTO getByDeviceName(String deviceName){
        log.info("method --- getByDeviceName() for device");
        return deviceConverter.convert(deviceRepository.findByDeviceName(deviceName).orElseThrow( () ->
                new NotFoundException("There is no such device in system")));
    }

    @Override
    public DeviceDTO getByIpAddress(String ipAddress) {
        log.info("method --- getByIpAddress() for device");
        return deviceConverter.convert(deviceRepository.findByIpAddress(ipAddress).orElseThrow(() ->
                new NotFoundException("There is no device with such IP address")));
    }

    @Override
    public List<DeviceDTO> findAll(){
        log.info("method --- findAll() for device");
        return deviceRepository.findAll().stream().map(deviceConverter::convert).collect(Collectors.toList());
    }

    @Override
    public void create(DeviceDTO deviceDTO){
        log.info("method --- create() for device");

        verify(deviceRepository.existsByDeviceName(deviceDTO.getDeviceName()),
                () -> new ConflictException("There is already such device name in system"));
        verify(deviceRepository.existsByIpAddress(deviceDTO.getIpAddress()),
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
        deviceEntity.setDeviceName(editedDevice.getDeviceName());
        deviceEntity.setProcessor(editedDevice.getProcessor());
        deviceEntity.setIpAddress(editedDevice.getIpAddress());
        deviceEntity.setIsPortable(editedDevice.getIsPortable());
        deviceEntity.setAccessLevel(editedDevice.getAccessLevel());
        deviceEntity.setOperatingSystem(editedDevice.getOperatingSystem());
        return deviceConverter.convert(deviceRepository.save(deviceEntity));
    }

    @Override
    public void delete(Long id) {
        log.info("method --- delete() for device");
        deviceRepository.deleteById(id);
    }

    @Override
    public void deleteByDeviceName(String deviceName) {
        log.info("method --- deleteByDeviceName() for device");
        deviceRepository.deleteByDeviceName(deviceName);
    }

    @Override
    public void deleteByIpAddress(String ipAddress) {
        log.info("method --- deleteByIpAddress() for device");
        deviceRepository.deleteByIpAddress(ipAddress);
    }
}