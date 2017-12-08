package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.stream.Collectors;

@Named
public class PostInConverter implements InConverter<PostInDto, PostEntity> {

    private final AuthorConverter authorConverter;

    @Inject
    public PostInConverter(final AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
    }

    @Override
    public PostEntity convert(final PostInDto dto) {
        return PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(Timestamp.valueOf(dto.getPublished()))
                .authors(dto.getAuthors().stream().map(authorConverter::convert).collect(Collectors.toSet()))
                .build();
    }
}
