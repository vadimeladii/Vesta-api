package com.vesta.repository;

import com.vesta.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAll();

    void deleteById(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}