package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface CacheableAuthorRepository extends JpaRepository<AuthorEntity, Long> {
    @Override
    @CacheEvict("authorsCache")
    <S extends AuthorEntity> S save(S entity);

    @Override
    @Cacheable("authorsCache")
    List<AuthorEntity> findAll();

    AuthorEntity findByUsername(String username);

    @Query("SELECT a from AuthorEntity a WHERE id in :ids")
    Set<AuthorEntity> findByIds(@Param("ids") Set<Long> ids);
}
