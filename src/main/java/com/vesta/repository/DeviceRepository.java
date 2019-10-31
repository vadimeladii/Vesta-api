package com.vesta.repository;

import com.vesta.repository.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {


    Optional<DeviceEntity> findByDeviceName(String deviceName);

    Optional<DeviceEntity> findByIpAddress(String ipAddress);

    List<DeviceEntity> findByOperatingSystem(String operatingSystem);

    List<DeviceEntity> findByAccessLevel(Byte accessLevel);


    void deleteById(Long id);

    void deleteByIpAddress(String ipAddress);

    boolean existsByDeviceName(String deviceName);

    boolean existsByIpAddress(String ipAddress);
}
