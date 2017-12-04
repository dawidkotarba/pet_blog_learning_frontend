package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.CommentDto;
import com.dawidkotarba.blog.model.entities.CommentEntity;

import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class CommentConverter implements Converter<CommentEntity, CommentDto> {

    List<CommentDto> convertToDtos(final List<CommentEntity> entities) {
        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    List<CommentEntity> convertToEntities(final List<CommentDto> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::convertToEntity)
                .collect(Collectors.toList());
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
