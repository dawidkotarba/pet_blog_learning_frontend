package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;

import javax.inject.Named;

@Named
public class PostInConverter implements InConverter<PostInDto, PostEntity> {

    @Override
    public PostEntity convert(final PostInDto dto) {
        return PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .build();
    }
}
