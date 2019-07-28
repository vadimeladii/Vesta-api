package com.vesta.repository;

import com.vesta.repository.entity.SubjectImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectImageRepository extends JpaRepository<SubjectImageEntity, Long> {

    Optional<SubjectImageEntity> findById(Long id);

    void deleteById(Long id);

    List<SubjectImageEntity> findAll();
}