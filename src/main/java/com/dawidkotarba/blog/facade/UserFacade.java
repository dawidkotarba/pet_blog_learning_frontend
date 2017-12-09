package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.UserInConverter;
import com.dawidkotarba.blog.converters.impl.UserOutConverter;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.dto.impl.UserOutDto;
import com.dawidkotarba.blog.model.entities.impl.UserEntity;
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
    private final UserInConverter userInConverter;
    private final UserOutConverter userOutConverter;

    @Inject
    UserFacade(final UserRepository userRepository, final UserInConverter userInConverter, final UserOutConverter userOutConverter) {
        this.userRepository = userRepository;
        this.userInConverter = userInConverter;
        this.userOutConverter = userOutConverter;
    }

    public Optional<UserOutDto> findByUsername(final String username) {
        Preconditions.checkNotNull(username);
        final UserEntity byUsername = userRepository.findByUsername(username);

        if (Objects.isNull(byUsername)) {
            return Optional.empty();
        }

        final UserOutDto userDto = userOutConverter.convert(byUsername);
        return Optional.of(userDto);
    }

    public List<UserOutDto> findAll() {
        final List<UserEntity> all = userRepository.findAll();

        return all.stream().map(userOutConverter::convert).collect(Collectors.toList());
    }

    public void add(final UserInDto userInDto) {
        userRepository.save(userInConverter.convert(userInDto));
    }
}
