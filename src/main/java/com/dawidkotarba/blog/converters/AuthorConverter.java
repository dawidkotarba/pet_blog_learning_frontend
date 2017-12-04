package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.AuthorDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;

import javax.inject.Named;

@Named
public class AuthorConverter implements Converter<AuthorEntity, AuthorDto> {

    @Override
    public AuthorDto convertToDto(final AuthorEntity entity) {
        return AuthorDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname()).build();
    }

    @Override
    public AuthorEntity convertToEntity(final AuthorDto dto) {
        return AuthorEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .build();
    }
}
