package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named
public class PostOutConverter implements OutConverter<PostEntity, PostOutDto> {

    private final AuthorConverter authorConverter;
    private final CommentConverter commentsConverter;

    @Inject
    public PostOutConverter(final AuthorConverter authorConverter, final CommentConverter commentsConverter) {
        this.authorConverter = authorConverter;
        this.commentsConverter = commentsConverter;
    }

    @Override
    public PostOutDto convert(final PostEntity entity) {
        return PostOutDto.builder()
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished().toLocalDateTime())
                .authors(entity.getAuthors().stream().map(authorConverter::convert).collect(Collectors.toSet()))
                .commentDtos(commentsConverter.convertToDtos(entity.getComments()))
                .build();
    }
}
