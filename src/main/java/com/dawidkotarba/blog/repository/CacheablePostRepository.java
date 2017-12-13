package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

public interface CacheablePostRepository extends JpaRepository<PostEntity, Long> {

    @Override
    @CacheEvict("postsCache")
    <S extends PostEntity> S save(S entity);

    @Override
    @Cacheable("postsCache")
    @Query("SELECT p from PostEntity p JOIN FETCH p.authors JOIN FETCH p.comments")
    List<PostEntity> findAll();

    PostEntity findBySubject(String subject);

    Set<PostEntity> findByPublishedBetween(Timestamp fromDate, Timestamp toDate);
}