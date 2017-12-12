package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CacheableUserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    @CacheEvict("usersCache")
    <S extends UserEntity> S save(S entity);

    @Override
    @Cacheable("usersCache")
    List<UserEntity> findAll();

    UserEntity findByUsername(String name);
}