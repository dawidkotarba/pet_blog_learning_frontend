package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface CacheablePostRepository extends JpaRepository<PostEntity, Long> {

    @Override
    @CacheEvict("postsCache")
    <S extends PostEntity> S save(S entity);

    @Override
    @CacheEvict("postsCache")
    <S extends PostEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("postsCache")
    List<PostEntity> findAll();

    PostEntity findBySubject(String subject);

    Set<PostEntity> findByPublishedBetween(Timestamp fromDate, Timestamp toDate);
}