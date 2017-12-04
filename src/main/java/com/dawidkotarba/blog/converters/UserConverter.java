package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.UserDto;
import com.dawidkotarba.blog.model.entities.UserEntity;

import javax.inject.Named;

@Named
public class UserConverter implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convertToDto(final UserEntity entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .build();
    }

    @Override
    public UserEntity convertToEntity(final UserDto dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .build();
    }
}
