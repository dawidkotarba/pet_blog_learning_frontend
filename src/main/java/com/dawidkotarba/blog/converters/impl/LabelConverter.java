package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.enums.Label;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.model.entities.impl.LabelEntity;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.CacheablePostRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class LabelConverter implements InConverter<LabelDto, LabelEntity>, OutConverter<LabelEntity, LabelDto> {

    private final CacheablePostRepository cacheablePostRepository;

    @Inject
    public LabelConverter(final CacheablePostRepository cacheablePostRepository) {
        this.cacheablePostRepository = cacheablePostRepository;
    }

    @Override
    public LabelEntity convert(final LabelDto dto) {
        final LabelEntity entity = LabelEntity.builder()
                .label(Arrays.asList(Label.values()).stream().filter(label -> label.getName().equals(dto.getLabel())).findFirst().get())
                .build();

        findAndPopulatePosts(dto, entity);

        return entity;
    }

    @Override
    public LabelDto convert(final LabelEntity entity) {
        return LabelDto.builder()
                .id(entity.getId())
                .label(entity.getLabel().getName())
                .build();
    }

    private void findAndPopulatePosts(final LabelDto dto, final LabelEntity entity) {
        if (dto.getPosts() != null) {
            Set<PostEntity> posts = dto.getPosts().stream()
                    .filter(Objects::nonNull)
                    .map(cacheablePostRepository::findById)
                     .map(Optional::get)
                    .collect(Collectors.toSet());
            entity.setPosts(posts);
        }
    }
}
