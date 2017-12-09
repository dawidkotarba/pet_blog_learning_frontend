package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.CommentInDto;
import com.dawidkotarba.blog.model.entities.impl.CommentEntity;
import com.dawidkotarba.blog.repository.PostRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class CommentInConverter implements InConverter<CommentInDto, CommentEntity> {

    @Inject
    private PostRepository postRepository;

    @Override
    public CommentEntity convert(final CommentInDto dto) {
        return CommentEntity.builder()
                .author(dto.getAuthor())
                .subject(dto.getSubject())
                .body(dto.getBody())
                .post(postRepository.findOne(dto.getPostId()))
                .published(Timestamp.valueOf(dto.getPublished()))
                .build();
    }

    Set<CommentEntity> convertToEntities(final Set<CommentInDto> dtos) {
        return dtos.stream()
                .filter(Objects::nonNull)
                .map(this::convert)
                .collect(Collectors.toSet());
    }
}
