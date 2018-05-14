package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.CacheableLabelRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

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

        populateLabelsIfExist(dto, entity);

        return entity;
    }

    private void populateLabelsIfExist(final PostInDto dto, final PostEntity entity) {
        if (dto.getLabels() != null) {
            final java.util.Set<LabelEntity> labels = dto.getLabels()
                    .filter(Objects::nonNull)
                    .map(cacheableLabelRepository::findById)
                    .map(Optional::get)
                    .toJavaSet();
            entity.setLabels(labels);
        }
    }
}
