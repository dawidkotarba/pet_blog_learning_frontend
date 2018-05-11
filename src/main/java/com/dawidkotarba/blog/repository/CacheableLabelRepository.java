package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

public interface CacheableLabelRepository extends BaseRepository<LabelEntity> {

    @Override
    @CacheEvict(value = "labelsCache", allEntries = true)
    <S extends LabelEntity> S save(S entity);

    @Override
    @CacheEvict(value = "labelsCache", allEntries = true)
    <S extends LabelEntity> S saveAndFlush(S entity);

    @Override
    @Query("SELECT DISTINCT l from LabelEntity l LEFT JOIN FETCH l.posts")
    @Cacheable("labelsCache")
    java.util.List<LabelEntity> findAll();
}
