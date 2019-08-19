package com.vesta.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "position_x")
    private Float positionX;

    @Column(name = "position_y")
    private Float positionY;

    @Column(name = "scale")
    private Float scale;

    @Column(name = "editable")
    private Boolean editable;

    @Column(name = "rotation")
    private Float rotation;

    @Column(name = "floor_id")
    private Long floorId;

    @Column(name = "additional")
    private String additional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_template_id", referencedColumnName = "id")
    private SubjectTemplateEntity subjectTemplateEntity;
}
