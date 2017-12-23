package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface CacheablePostRepository extends BaseRepository<PostEntity> {

    @Override
    @CacheEvict("postsCache")
    <S extends PostEntity> S save(S entity);

    @Override
    @CacheEvict("postsCache")
    <S extends PostEntity> S saveAndFlush(S entity);

    @Override
    @Cacheable("postsCache")
    @Query("SELECT DISTINCT p from PostEntity p LEFT JOIN FETCH p.authors LEFT JOIN FETCH p.comments LEFT JOIN FETCH p.labels")
    List<PostEntity> findAll();

    PostEntity findBySubject(String subject);

    Set<PostEntity> findByPublishedBetween(LocalDateTime fromDate, LocalDateTime toDate);
}