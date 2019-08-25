package com.vesta.repository;

import com.vesta.repository.entity.FloorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepository extends JpaRepository<FloorEntity, Long> {

    List<FloorEntity> findAll();

    Optional<FloorEntity> findById(Long id);

    void deleteById(Long id);

    @Modifying
    @Query("delete from FloorEntity f where f.id in :ids")
    void deleteByIds(List<Long> ids);
}