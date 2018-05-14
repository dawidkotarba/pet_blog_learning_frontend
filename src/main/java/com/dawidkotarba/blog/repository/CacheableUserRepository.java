package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

public interface CacheableUserRepository extends BaseRepository<UserEntity> {
    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    <S extends UserEntity> S save(S entity);

    @Override
    @CacheEvict(value = "usersCache", allEntries = true)
    <S extends UserEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("usersCache")
    @Query("SELECT DISTINCT u from UserEntity u LEFT JOIN FETCH u.authorities")
    java.util.List<UserEntity> findAll();

    @Cacheable(value = "usersCache", key = "#p0")
    UserEntity findByUsername(String name);
}
