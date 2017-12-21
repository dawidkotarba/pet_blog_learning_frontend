package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CacheableAuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Override
    @CacheEvict("authorsCache")
    <S extends AuthorEntity> S save(S entity);

    @Override
    @CacheEvict("authorsCache")
    <S extends AuthorEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("authorsCache")
    List<AuthorEntity> findAll();

    List<AuthorEntity> findAllByUsernameIn(Iterable<String> usernames);

    AuthorEntity findByUsername(String username);

    List<AuthorEntity> findAllByUsernameStartingWithIgnoreCase(String username);
}
