package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.UserDto;
import com.dawidkotarba.blog.model.entities.UserEntity;

import javax.inject.Named;

@Named
public class UserConverter implements InConverter<UserDto, UserEntity>, OutConverter<UserEntity, UserDto> {

    @Override
    public UserDto convert(final UserEntity entity) {
        return UserDto.builder()
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .role(entity.getRole())
                .enabled(entity.isEnabled())
                .build();
    }

    @Override
    public UserEntity convert(final UserDto dto) {
        return UserEntity.builder()
                .username(dto.getUsername())
                .firstname(dto.getFirstname())
                .lastname(dto.getLastname())
                .role(dto.getRole())
                .enabled(dto.isEnabled())
                .build();
    }
}
