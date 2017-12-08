package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.AuthorDto;
import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;

import javax.inject.Named;

@Named
public class AuthorConverter implements InConverter<AuthorDto, AuthorEntity>, OutConverter<AuthorEntity, AuthorDto> {

    @Override
    public AuthorDto convert(final AuthorEntity entity) {
        return AuthorDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname()).build();
    }

    @Override
    public AuthorEntity convert(final AuthorDto dto) {
        return AuthorEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .build();
    }
}
