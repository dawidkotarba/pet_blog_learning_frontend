package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.UserConverter;
import com.dawidkotarba.blog.model.dto.impl.UserDto;
import com.dawidkotarba.blog.model.entities.UserEntity;
import com.dawidkotarba.blog.repository.UserRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
public class UserFacade {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Inject
    UserFacade(final UserRepository userRepository, final UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public Optional<UserDto> findByUsername(final String username) {
        Preconditions.checkNotNull(username);
        final UserEntity byUsername = userRepository.findByUsername(username);

        if (Objects.isNull(byUsername)) {
            return Optional.empty();
        }

        final UserDto userDto = userConverter.convert(byUsername);
        return Optional.of(userDto);
    }

    public List<UserDto> findAll() {
        final List<UserEntity> all = userRepository.findAll();

        return all.stream().map(userConverter::convert).collect(Collectors.toList());
    }
}
