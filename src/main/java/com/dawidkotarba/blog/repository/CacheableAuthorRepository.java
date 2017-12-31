package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Set;

public interface CacheableAuthorRepository extends BaseRepository<AuthorEntity> {
    @Override
    @CacheEvict("authorsCache")
    <S extends AuthorEntity> S save(S entity);

    @Override
    @CacheEvict("authorsCache")
    <S extends AuthorEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("authorsCache")
    List<AuthorEntity> findAll();

    Set<AuthorEntity> findAllByUsernameIn(Iterable<String> usernames);

    AuthorEntity findByUsername(String username);

    Set<AuthorEntity> findAllByUsernameStartingWithIgnoreCase(String username);
}
