package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CacheableLabelRepository extends BaseRepository<LabelEntity> {

    @Override
    @Query("SELECT DISTINCT l from LabelEntity l LEFT JOIN FETCH l.posts")
    List<LabelEntity> findAll();
}
