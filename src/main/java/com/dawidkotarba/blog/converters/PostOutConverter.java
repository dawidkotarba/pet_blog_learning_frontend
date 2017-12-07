package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.PostOutDto;
import com.dawidkotarba.blog.model.entities.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class PostOutConverter implements Converter<PostEntity, PostOutDto> {

    private final AuthorConverter authorConverter;
    private final CommentConverter commentsConverter;

    @Inject
    public PostOutConverter(final AuthorConverter authorConverter, final CommentConverter commentsConverter) {
        this.authorConverter = authorConverter;
        this.commentsConverter = commentsConverter;
    }

    @Override
    public PostOutDto convertToDto(final PostEntity entity) {
        return PostOutDto.builder()
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .authors(entity.getAuthors().stream().map(authorConverter::convertToDto).collect(Collectors.toSet()))
                .commentDtos(commentsConverter.convertToDtos(entity.getComments()))
                .build();
    }

    @Override
    public PostEntity convertToEntity(final PostOutDto dto) {
        final PostEntity entity = PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .authors(dto.getAuthors().stream().map(authorConverter::convertToEntity).collect(Collectors.toSet()))
                .build();

        if (Objects.nonNull(dto.getCommentDtos())) {
            entity.setComments(commentsConverter.convertToEntities(dto.getCommentDtos()));
        }

        return entity;
    }
}
