package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.model.entities.impl.AbstractEntity;
import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import com.dawidkotarba.blog.repository.CacheablePostRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class LabelConverter implements InConverter<LabelDto, LabelEntity>, OutConverter<LabelEntity, LabelDto> {

    private final CacheablePostRepository cacheablePostRepository;

    @Inject
    public LabelConverter(final CacheablePostRepository cacheablePostRepository) {
        this.cacheablePostRepository = cacheablePostRepository;
    }

    @Override
    public LabelEntity convert(final LabelDto dto) {
        final LabelEntity entity = LabelEntity.builder()
                .name(dto.getName())
                .build();

        findAndPopulatePosts(dto, entity);

        return entity;
    }

    @Override
    public LabelDto convert(final LabelEntity entity) {
        final LabelDto dto = LabelDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();

        populatePostsIfExist(entity, dto);

        return dto;
    }

    private void findAndPopulatePosts(final LabelDto dto, final LabelEntity entity) {
        if (dto.getPosts() != null) {
            entity.setPosts(dto.getPosts().stream()
                    .filter(Objects::nonNull)
                    .map(cacheablePostRepository::findOne)
                    .collect(Collectors.toSet()));
        }
    }

    private void populatePostsIfExist(final LabelEntity entity, final LabelDto dto) {
        if (entity.getPosts() != null) {
            dto.setPosts(entity.getPosts().stream()
                    .filter(Objects::nonNull)
                    .map(AbstractEntity::getId)
                    .collect(Collectors.toSet()));
        }
    }
}
