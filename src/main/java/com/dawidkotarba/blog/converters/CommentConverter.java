package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.dto.CommentDto;
import com.dawidkotarba.blog.model.entities.CommentEntity;

import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class CommentConverter implements Converter<CommentEntity, CommentDto> {

    List<CommentDto> convertListToDto(final List<CommentEntity> source) {
        final List<CommentDto> dtos;
        dtos = source.stream().map(comment -> convertToDto(comment)).collect(Collectors.toList());
        return dtos;
    }

    List<CommentEntity> convertListToEntity(final List<CommentDto> source) {
        final List<CommentEntity> entities;
        entities = source.stream().map(comment -> convertToEntity(comment)).collect(Collectors.toList());
        return entities;
    }

    @Override
    public CommentDto convertToDto(final CommentEntity entity) {
        final CommentDto dto = CommentDto.builder()
                .author(entity.getAuthor())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .build();
        return dto;
    }

    @Override
    public CommentEntity convertToEntity(final CommentDto dto) {
        final CommentEntity entity = CommentEntity.builder()
                .author(dto.getAuthor())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .build();
        return entity;
    }
}
