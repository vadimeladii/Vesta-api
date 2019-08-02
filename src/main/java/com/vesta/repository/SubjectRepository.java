package com.vesta.repository;

import com.vesta.repository.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findById(Long id);

    void deleteById(Long id);

    List<SubjectEntity> findAll();

    List<SubjectEntity> findAllByFloorId(Long floorId);

    void deleteAllById(List<Long> id);
}
