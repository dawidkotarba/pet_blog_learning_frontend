package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dawid Kotarba on 17.01.2016.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String name);
}
