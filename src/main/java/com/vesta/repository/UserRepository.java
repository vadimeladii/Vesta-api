package com.vesta.repository;

import com.vesta.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("select case when count(u)> 0 then true else false end from UserEntity u where u.password=:password " +
            "and (u.email=:email or u.username=:username)")
    boolean existsByUsernameOrEmailAndPassword(@Param("username") String username,
                                               @Param("email") String email,
                                               @Param("password") String password);
}