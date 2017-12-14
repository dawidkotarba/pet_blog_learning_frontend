package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CacheableLabelRepository extends JpaRepository<LabelEntity, Long> {

    @Override
    @Query("SELECT l from LabelEntity l JOIN FETCH l.posts")
    List<LabelEntity> findAll();
}
