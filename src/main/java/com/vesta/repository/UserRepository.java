package com.vesta.repository;

import com.vesta.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findById(Long id);

    Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    List<UserEntity> findAll();

    void deleteById(Long id);
}