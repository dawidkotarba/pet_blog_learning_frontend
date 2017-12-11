package com.dawidkotarba.blog.converters.impl;

import com.dawidkotarba.blog.converters.InConverter;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import com.dawidkotarba.blog.repository.AuthorityRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.stream.Collectors;

@Named
public class UserInConverter implements InConverter<UserInDto, UserEntity> {

    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Inject
    public UserInConverter(final AuthorityRepository authorityRepository, final BCryptPasswordEncoder passwordEncoder) {
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity convert(final UserInDto userInDto) {
        return UserEntity.builder()
                .username(userInDto.getUsername())
                .firstname(userInDto.getFirstname())
                .lastname(userInDto.getLastname())
                .password(passwordEncoder.encode(userInDto.getPassword()))
                .enabled(userInDto.isEnabled())
                .authorities(userInDto.getAuthorities().stream()
                        .map(authority -> authorityRepository.findByAuthority(authority))
                        .collect(Collectors.toSet()))
                .build();
    }
}
