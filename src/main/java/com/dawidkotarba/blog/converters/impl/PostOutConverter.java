package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.AuthorDto;
import com.dawidkotarba.blog.model.dto.impl.CommentOutDto;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import com.dawidkotarba.blog.model.entities.impl.CommentEntity;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

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
        final Set<AuthorDto> authors = Stream.ofAll(entity.getAuthors())
                .map(authorConverter::convert)
                .toSet();
        final Set<CommentEntity> commentEntities = HashSet.ofAll(entity.getComments());
        final Set<CommentOutDto> commentDtos = commentsConverter.convertToDtos(commentEntities);
        final PostOutDto dto = PostOutDto.builder()
                .id(entity.getId())
                .subject(entity.getSubject())
                .body(entity.getBody())
                .published(entity.getPublished())
                .authors(authors)
                .commentDtos(commentDtos)
                .build();

        populateLabelsIfExist(entity, dto);

        return dto;
    }

    private void populateLabelsIfExist(final PostEntity entity, final PostOutDto dto) {
        if (entity.getLabels() != null) {
            final Set<LabelDto> labels = Stream.ofAll(entity.getLabels())
                    .filter(Objects::nonNull)
                    .map(labelConverter::convert)
                    .toSet();
            dto.setLabels(labels);
        }
    }
}
