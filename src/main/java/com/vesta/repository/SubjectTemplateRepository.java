package com.vesta.repository;

import com.vesta.repository.entity.SubjectTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectTemplateRepository extends JpaRepository<SubjectTemplateEntity, Long> {

    Optional<SubjectTemplateEntity> findById(Long id);

    Optional<SubjectTemplateEntity> getByImage (String image);

    void deleteById(Long id);

    List<SubjectTemplateEntity> findAll();
}