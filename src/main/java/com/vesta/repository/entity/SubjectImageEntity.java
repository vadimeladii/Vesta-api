package com.vesta.repository.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "subject_templates")
@Data
public class SubjectImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "image")
    private String image;
}
