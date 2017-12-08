package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.CommentDto;
import com.dawidkotarba.blog.model.entities.CommentEntity;

import javax.inject.Named;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class CommentConverter implements Converter<CommentEntity, CommentDto> {

    Set<CommentDto> convertToDtos(final Set<CommentEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::convertToDto)
                .collect(Collectors.toSet());
    }

    Set<CommentEntity> convertToEntities(final Set<CommentDto> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::convertToEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public CommentDto convertToDto(final CommentEntity entity) {
        return CommentDto.builder()
                .author(entity.getAuthor())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .build();
    }

    @Override
    public CommentEntity convertToEntity(final CommentDto dto) {
        return CommentEntity.builder()
                .author(dto.getAuthor())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .build();
    }
}
