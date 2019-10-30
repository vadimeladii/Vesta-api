package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "device")
@Getter
@Setter
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "device_name")
    private String device_name;

    @Column(name = "operating_system")
    private String operating_system;

    @Column(name = "ip_address")
    private String ip_address;

    @Column(name = "access_level")
    private Byte access_level;

    @Column(name = "processor")
    private String processor;

    @Column(name = "is_portable")
    private Boolean is_portable;
}
