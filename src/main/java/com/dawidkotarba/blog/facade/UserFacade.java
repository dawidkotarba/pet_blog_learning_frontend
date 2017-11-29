package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.dto.UserDto;
import com.dawidkotarba.blog.model.entities.UserEntity;
import com.dawidkotarba.blog.repository.UserRepository;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Named
public class UserFacade {

    private final UserRepository userRepository;

    @Inject
    public UserFacade(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDto> findByName(final String name) {
        final List<UserEntity> byUsername = userRepository.findByUsername(name);
        if (byUsername.isEmpty()) {
            return Optional.empty();
        }
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(byUsername.get(0), userDto);
        return Optional.of(userDto);
    }

    public List<UserDto> findAll() {
        final List<UserEntity> all = userRepository.findAll();
        final List<UserDto> result = new ArrayList<>();
        all.forEach(entity -> {
            final UserDto userDto = new UserDto();
            BeanUtils.copyProperties(entity, userDto);
            result.add(userDto);
        });
        return result;
    }
}
