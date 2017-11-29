package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dawid Kotarba on 17.01.2016.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUsername(String name);
}
