package com.vesta.repository;

import com.vesta.repository.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {

    Optional<SubjectEntity> findById(Long id);

    List<SubjectEntity> findAll();

    List<SubjectEntity> findByFloorId(Long floorId);

    @Modifying
    @Query("delete from SubjectEntity s where s.id in :ids")
    void deleteAll(List<Long> ids);
}
