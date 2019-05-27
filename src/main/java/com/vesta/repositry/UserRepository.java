package com.vesta.repositry;

import com.vesta.repositry.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface UserRepository {
//        extends CrudRepository<UserEntity, Long> {
    boolean existsByUsernameAndPassword(String name, String password);
}

