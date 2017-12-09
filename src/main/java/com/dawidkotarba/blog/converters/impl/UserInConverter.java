package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.entities.impl.UserEntity;

import javax.inject.Named;

@Named
public class UserInConverter implements InConverter<UserInDto, UserEntity> {

    @Override
    public UserEntity convert(final UserInDto userInDto) {
        return UserEntity.builder()
                .username(userInDto.getUsername())
                .firstname(userInDto.getFirstname())
                .lastname(userInDto.getLastname())
                .password(userInDto.getPassword())
                .enabled(userInDto.isEnabled())
                .build();
    }
}
