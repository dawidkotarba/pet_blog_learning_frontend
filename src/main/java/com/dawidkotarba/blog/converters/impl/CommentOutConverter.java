package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.CommentOutDto;
import com.dawidkotarba.blog.model.entities.impl.CommentEntity;
import io.vavr.collection.Set;

import javax.inject.Named;
import java.util.Objects;

@Named
public class CommentOutConverter implements OutConverter<CommentEntity, CommentOutDto> {

    @Override
    public CommentOutDto convert(final CommentEntity entity) {
        return CommentOutDto.builder()
                .id(entity.getId())
                .author(entity.getAuthor())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .build();
    }

    Set<CommentOutDto> convertToDtos(final Set<CommentEntity> entities) {
        return entities.filter(Objects::nonNull).map(this::convert);
    }
}
