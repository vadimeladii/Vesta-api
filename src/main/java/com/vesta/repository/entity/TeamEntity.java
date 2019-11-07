package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "team", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
@Getter
@Setter
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "project_and_project_role")
    private String projectName;

    @Column(name = "office")
    private String office;

    @Column (name = "phone")
    private String phone;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "has_office_keys")
    private String hasOfficeKeys;

    @Column(name = "work_at_pentalog_since")
    private String workAtPentalogSince;

    @Column(name = "date_of_birth")
    private String dateOfBirth;
}