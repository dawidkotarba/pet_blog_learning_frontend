package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.dto.CommentDto;
import com.dawidkotarba.blog.model.entities.CommentEntity;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CommentConverter implements Converter<CommentEntity, CommentDto> {

    List<CommentDto> convertListToDto(final List<CommentEntity> source) {
        return source.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    List<CommentEntity> convertListToEntity(final List<CommentDto> source) {
        return source.stream().map(this::convertToEntity).collect(Collectors.toList());
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
