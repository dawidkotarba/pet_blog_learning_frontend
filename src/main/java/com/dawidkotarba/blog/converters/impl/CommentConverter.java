package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.CommentDto;
import com.dawidkotarba.blog.model.entities.impl.CommentEntity;

import javax.inject.Named;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class CommentConverter implements InConverter<CommentDto, CommentEntity>, OutConverter<CommentEntity, CommentDto> {

    Set<CommentDto> convertToDtos(final Set<CommentEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    Set<CommentEntity> convertToEntities(final Set<CommentDto> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public CommentDto convert(final CommentEntity entity) {
        return CommentDto.builder()
                .author(entity.getAuthor())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished().toLocalDateTime())
                .build();
    }

    @Override
    public CommentEntity convert(final CommentDto dto) {
        return CommentEntity.builder()
                .author(dto.getAuthor())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(Timestamp.valueOf(dto.getPublished()))
                .build();
    }
}
