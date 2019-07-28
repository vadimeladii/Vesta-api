package com.vesta.repository;

import com.vesta.repository.entity.FloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Long> {

    List<FloorEntity> findAll();

    Optional<FloorEntity> findById(Long id);

    void deleteById(Long id);
}