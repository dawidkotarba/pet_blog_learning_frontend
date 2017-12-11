package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.OutConverter;
import com.dawidkotarba.blog.model.dto.impl.UserOutDto;
import com.dawidkotarba.blog.model.entities.impl.UserEntity;

import javax.inject.Named;

@Named
public class UserOutConverter implements OutConverter<UserEntity, UserOutDto> {

    @Override
    public UserOutDto convert(final UserEntity userEntity) {
        return UserOutDto.builder()
                .username(userEntity.getUsername())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .enabled(userEntity.isEnabled())
                .build();
    }
}
