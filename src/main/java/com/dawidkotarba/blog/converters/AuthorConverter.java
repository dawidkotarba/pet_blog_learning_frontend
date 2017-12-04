package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.dto.AuthorDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;

import javax.inject.Named;

@Named
public class AuthorConverter implements Converter<AuthorEntity, AuthorDto> {

    @Override
    public AuthorDto convertToDto(final AuthorEntity entity) {
        final AuthorDto dto = AuthorDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname()).build();
        return dto;
    }

    @Override
    public AuthorEntity convertToEntity(final AuthorDto dto) {
        final AuthorEntity entity = AuthorEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .build();
        return entity;
    }
}
