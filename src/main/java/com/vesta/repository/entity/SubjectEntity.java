package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
public class  SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "position_x")
    private Integer position_x;

    @Column(name = "position_y")
    private Integer position_y;

    @Column(name = "scale")
    private Float scale;

    @Column(name = "editable")
    private Boolean editable;

    @Column(name = "rotation")
    private Integer rotation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_template_id", referencedColumnName = "id")
    private SubjectTemplateEntity subjectTemplateEntity;
}
