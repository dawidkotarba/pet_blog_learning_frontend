package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.PostInDto;
import com.dawidkotarba.blog.model.entities.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PostInConverter implements Converter<PostEntity, PostInDto> {

    private final AuthorConverter authorConverter;

    @Inject
    public PostInConverter(final AuthorConverter authorConverter) {
        this.authorConverter = authorConverter;
    }

    @Override
    public PostInDto convertToDto(final PostEntity entity) {
        return PostInDto.builder()
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .author(authorConverter.convertToDto(entity.getAuthor()))
                .build();
    }

    @Override
    public PostEntity convertToEntity(final PostInDto dto) {
        return PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .author(authorConverter.convertToEntity(dto.getAuthor()))
                .build();
    }
}
