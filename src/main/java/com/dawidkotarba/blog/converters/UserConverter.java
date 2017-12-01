package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.dto.UserDto;
import com.dawidkotarba.blog.model.entities.UserEntity;

public class UserConverter implements Converter<UserEntity, UserDto> {
    @Override
    public UserDto convertToDto(final UserEntity entity) {
        final UserDto dto = UserDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .build();
        return dto;
    }

    @Override
    public UserEntity convertToEntity(final UserDto dto) {
        final UserEntity entity = UserEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .build();
        return entity;
    }
}
