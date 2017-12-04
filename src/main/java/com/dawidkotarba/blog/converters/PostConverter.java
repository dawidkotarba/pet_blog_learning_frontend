package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.model.entities.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class PostConverter implements Converter<PostEntity, PostDto> {

    @Inject
    private AuthorConverter authorConverter;

    @Inject
    private CommentConverter commentsConverter;

    @Override
    public PostDto convertToDto(final PostEntity entity) {
        return PostDto.builder()
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .author(authorConverter.convertToDto(entity.getAuthor()))
                .commentDtos(commentsConverter.convertListToDto(entity.getComments()))
                .build();
    }

    @Override
    public PostEntity convertToEntity(final PostDto dto) {
        return PostEntity.builder()
                .subject(dto.getSubject())
                .body(dto.getBody())
                .published(dto.getPublished())
                .author(authorConverter.convertToEntity(dto.getAuthor()))
                .comments(commentsConverter.convertListToEntity(dto.getCommentDtos()))
                .build();
    }
}
