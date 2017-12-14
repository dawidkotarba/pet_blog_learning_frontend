package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.stream.Collectors;

@Named
public class PostOutConverter implements OutConverter<PostEntity, PostOutDto> {

    private final AuthorConverter authorConverter;
    private final CommentOutConverter commentsConverter;
    private final LabelConverter labelConverter;

    @Inject
    public PostOutConverter(final AuthorConverter authorConverter, final CommentOutConverter commentsConverter,
                            final LabelConverter labelConverter) {
        this.authorConverter = authorConverter;
        this.commentsConverter = commentsConverter;
        this.labelConverter = labelConverter;
    }

    @Override
    public PostOutDto convert(final PostEntity entity) {
        final PostOutDto dto = PostOutDto.builder()
                .id(entity.getId())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .authors(entity.getAuthors().stream().map(authorConverter::convert).collect(Collectors.toSet()))
                .commentDtos(commentsConverter.convertToDtos(entity.getComments()))
                .build();

        if (entity.getLabels() != null) {
            dto.setLabels(entity.getLabels().stream()
                    .filter(Objects::nonNull)
                    .map(labelConverter::convert)
                    .collect(Collectors.toSet()));
        }

        return dto;
    }
}
