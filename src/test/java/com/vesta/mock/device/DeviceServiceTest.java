package com.vesta.mock.device;

import com.vesta.common.DeviceUtilData;
import com.vesta.exception.ConflictException;
import com.vesta.exception.NotFoundException;
import com.vesta.exception.VestaException;
import com.vesta.repository.DeviceRepository;
import com.vesta.repository.entity.DeviceEntity;
import com.vesta.service.DeviceService;
import com.vesta.service.converter.DeviceConverter;
import com.vesta.service.dto.DeviceDTO;
import com.vesta.service.impl.DeviceServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {
    private DeviceService deviceService;

    @Mock
    private DeviceRepository deviceRepository;

    private DeviceConverter deviceConverter = new DeviceConverter();

    @Before
    public void setUp(){ deviceService = new DeviceServiceImpl(deviceRepository, deviceConverter); }

    @Test
    public void test_findByDeviceName_validDeviceName(){
        DeviceEntity deviceEntity = DeviceUtilData.deviceEntity();
        Mockito.when(deviceRepository.findByDeviceName(deviceEntity.getDeviceName()))
                .thenReturn(Optional.of(deviceEntity));
        DeviceDTO deviceDTO = deviceService.getByDeviceName(deviceEntity.getDeviceName());

        assertNotNull(deviceDTO);
        assertThat(deviceEntity.getDeviceName(), is(deviceDTO.getDeviceName()));
        assertThat(deviceEntity.getId(), is(deviceDTO.getId()));
        assertThat(deviceEntity.getIpAddress(), is(deviceDTO.getIpAddress()));
        assertThat(deviceEntity.getOperatingSystem(), is(deviceDTO.getOperatingSystem()));
        assertThat(deviceEntity.getProcessor(), is(deviceDTO.getProcessor()));
        assertThat(deviceEntity.getAccessLevel(), is(deviceDTO.getAccessLevel()));
        assertThat(deviceEntity.getIsPortable(), is(deviceDTO.getIsPortable()));
        verify(deviceRepository).findByDeviceName(deviceEntity.getDeviceName());
    }

    @Test(expected = VestaException.class)
    public void test_findByDeviceName_invalidDeviceName(){
        deviceService.getByDeviceName(RandomStringUtils.randomAlphabetic(15));
    }

    @Test
    public void test_findByIpAddress_validIpAddress(){
        DeviceEntity deviceEntity = DeviceUtilData.deviceEntity();
        Mockito.when(deviceRepository.findByDeviceName(deviceEntity.getDeviceName()))
                .thenReturn(Optional.of(deviceEntity));

        DeviceDTO deviceDTO = deviceService.getByDeviceName(deviceEntity.getDeviceName());

        assertNotNull(deviceDTO);
        assertThat(deviceEntity.getDeviceName(), is(deviceDTO.getDeviceName()));
        assertThat(deviceEntity.getId(), is(deviceDTO.getId()));
        assertThat(deviceEntity.getIpAddress(), is(deviceDTO.getIpAddress()));
        assertThat(deviceEntity.getOperatingSystem(), is(deviceDTO.getOperatingSystem()));
        assertThat(deviceEntity.getProcessor(), is(deviceDTO.getProcessor()));
        assertThat(deviceEntity.getAccessLevel(), is(deviceDTO.getAccessLevel()));
        assertThat(deviceEntity.getIsPortable(), is(deviceDTO.getIsPortable()));
        verify(deviceRepository).findByDeviceName(deviceEntity.getDeviceName());
    }

    @Test(expected = VestaException.class)
    public void test_findById_invalidIpAddress() {
        deviceService.getByIpAddress(RandomStringUtils.randomAlphabetic(15));
    }

    @Test
    public void test_findById_validId(){
        DeviceEntity deviceEntity = DeviceUtilData.deviceEntity();
        Mockito.when(deviceRepository.findByDeviceName(deviceEntity.getDeviceName()))
                .thenReturn(Optional.of(deviceEntity));
        DeviceDTO deviceDTO = deviceService.getByDeviceName(deviceEntity.getDeviceName());

        assertNotNull(deviceDTO);
        assertThat(deviceEntity.getDeviceName(), is(deviceDTO.getDeviceName()));
        assertThat(deviceEntity.getId(), is(deviceDTO.getId()));
        assertThat(deviceEntity.getIpAddress(), is(deviceDTO.getIpAddress()));
        assertThat(deviceEntity.getOperatingSystem(), is(deviceDTO.getOperatingSystem()));
        assertThat(deviceEntity.getProcessor(), is(deviceDTO.getProcessor()));
        assertThat(deviceEntity.getAccessLevel(), is(deviceDTO.getAccessLevel()));
        assertThat(deviceEntity.getIsPortable(), is(deviceDTO.getIsPortable()));
        verify(deviceRepository).findByDeviceName(deviceEntity.getDeviceName());
    }

    @Test(expected = VestaException.class)
    public void test_findById_invalidId(){ deviceService.getById(null); }

    @Test
    public void test_findAll_valid(){
        DeviceEntity deviceEntityFirst = DeviceUtilData.deviceEntity();
        DeviceEntity deviceEntitySecond = DeviceUtilData.deviceEntity();

        Mockito.when(deviceRepository.findAll()).thenReturn(List.of(deviceEntityFirst, deviceEntitySecond));
        List<DeviceDTO> devices = deviceService.findAll();

        assertEquals(devices.size(), 2);
        assertNotNull(devices.get(0));
        assertNotNull(devices.get(1));
        assertThat(deviceEntityFirst.getId(), is(devices.get(0).getId()));
        assertThat(deviceEntityFirst.getDeviceName(), is(devices.get(0).getDeviceName()));
        assertThat(deviceEntityFirst.getOperatingSystem(), is(devices.get(0).getOperatingSystem()));
        assertThat(deviceEntityFirst.getProcessor(), is(devices.get(0).getProcessor()));
        assertThat(deviceEntityFirst.getIpAddress(), is(devices.get(0).getIpAddress()));
        assertThat(deviceEntityFirst.getAccessLevel(), is(devices.get(0).getAccessLevel()));
        assertThat(deviceEntityFirst.getIsPortable(), is(devices.get(0).getIsPortable()));
    }

    @Test(expected = ConflictException.class)
    public void test_create_invalidDeviceName() {
        DeviceDTO deviceDTO = DeviceUtilData.deviceDTO();
        Mockito.when(deviceRepository.existsByDeviceName(deviceDTO.getDeviceName()))
                .thenReturn(Boolean.TRUE);
        deviceService.create(deviceDTO);
    }

    @Test
    public void test_create_valid(){
        DeviceDTO deviceDTO = DeviceUtilData.deviceDTO();
        Mockito.when(deviceRepository.existsByDeviceName(deviceDTO.getDeviceName()))
                .thenReturn(Boolean.FALSE);
        Mockito.when(deviceRepository.existsByIpAddress(deviceDTO.getIpAddress()))
                .thenReturn(Boolean.FALSE);
        deviceService.create(deviceDTO);
    }

    @Test(expected = NotFoundException.class)
    public void test_update_invalidId(){
        DeviceDTO deviceDTO = DeviceUtilData.deviceDTO();
        Mockito.when(deviceRepository.findById(deviceDTO.getId()))
                .thenReturn(Optional.empty());
        deviceService.update(deviceDTO.getId(), deviceDTO);
    }

    @Test
    public void test_update_valid(){
        DeviceDTO deviceDTO = DeviceUtilData.deviceDTO();
        Mockito.when(deviceRepository.findById(deviceDTO.getId()))
                .thenReturn(Optional.of(DeviceUtilData.deviceEntity()));
        deviceService.update(deviceDTO.getId(), deviceDTO);
    }
}