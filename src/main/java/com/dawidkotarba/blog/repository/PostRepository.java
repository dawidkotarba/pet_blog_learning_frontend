package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findBySubject(String subject);
}
