package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.Set;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    Set<PostEntity> findBySubject(String subject);

    Set<PostEntity> findByPublishedBetween(Timestamp fromDate, Timestamp toDate);
}
