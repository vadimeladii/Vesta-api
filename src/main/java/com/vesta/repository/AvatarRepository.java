package com.vesta.repository;

import com.vesta.repository.entity.AvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvatarRepository extends JpaRepository<AvatarEntity, Long> {

    @Query("select s from AvatarEntity s where s.userEntity.id = :userId")
    Optional<AvatarEntity> findByUserEntity(Long userId);
}
