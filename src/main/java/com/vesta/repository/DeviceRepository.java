package com.vesta.repository;

import com.vesta.repository.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {


    Optional<DeviceEntity> findByDeviceName(String device_name);

    Optional<DeviceEntity> findByIpAddress(String ip_address);

    List<DeviceEntity> findByOperatingSystem(String operating_system);

    List<DeviceEntity> findByAccessLevel(Byte access_level);


    void deleteById(Long id);

    void deleteByIpAddress(String ip_address);

    boolean existsByDeviceName(String device_name);

    boolean existsByIpAddress(String ip_address);
}
