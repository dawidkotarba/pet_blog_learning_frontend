package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository extends BaseRepository<CommentEntity> {

    Page<CommentEntity> findByPostId(Long post, Pageable pageable);
}
