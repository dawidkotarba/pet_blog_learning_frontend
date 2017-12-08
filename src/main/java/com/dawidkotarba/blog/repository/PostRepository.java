package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findBySubject(String subject);

    List<PostEntity> findByPublishedBetween(Timestamp fromDate, Timestamp toDate);
}
