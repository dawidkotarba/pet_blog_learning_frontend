package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import io.vavr.collection.Set;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface CacheableAuthorRepository extends BaseRepository<AuthorEntity> {
    @Override
    @CacheEvict(value = "authorsCache", allEntries = true)
    <S extends AuthorEntity> S save(S entity);

    @Override
    @CacheEvict(value = "authorsCache", allEntries = true)
    <S extends AuthorEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("authorsCache")
    java.util.List<AuthorEntity> findAll();

    @Cacheable(value = "authorsCache", key = "#p0")
    Set<AuthorEntity> findAllByUsernameStartingWithIgnoreCase(String username);
}
