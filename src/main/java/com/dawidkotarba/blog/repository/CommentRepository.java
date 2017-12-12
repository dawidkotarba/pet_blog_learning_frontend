package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    Page<CommentEntity> findByPostId(Long post, Pageable pageable);
}
