package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Setter
@Getter
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "companyId")
    private List<FloorEntity> floors = new ArrayList<>();
}
