package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.CacheableLabelRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class PostInConverter implements InConverter<PostInDto, PostEntity> {

    private final CacheableLabelRepository cacheableLabelRepository;

    @Inject
    public PostInConverter(final CacheableLabelRepository cacheableLabelRepository) {
        this.cacheableLabelRepository = cacheableLabelRepository;
    }

    @Override
    public PostEntity convert(final PostInDto dto) {
        final PostEntity entity = PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .build();

        if (dto.getLabels() != null) {
            entity.setLabels(dto.getLabels().stream()
                    .filter(Objects::nonNull)
                    .map(cacheableLabelRepository::findOne)
                    .collect(Collectors.toSet()));
        }

        return entity;
    }
}
