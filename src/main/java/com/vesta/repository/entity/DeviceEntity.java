package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;

@Entity
@Table(name = "devices")
@Getter
@Setter
public class DeviceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
}
