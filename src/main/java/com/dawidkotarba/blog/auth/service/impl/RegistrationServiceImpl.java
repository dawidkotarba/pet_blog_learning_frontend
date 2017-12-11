package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import com.dawidkotarba.blog.repository.UserRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegistrationServiceImpl {

    private final UserRepository userRepository;

    @Inject
    public RegistrationServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(final UserEntity userEntity) {
        Preconditions.checkNotNull(userEntity);
        Preconditions.checkNotNull(userEntity.getAuthorities());

        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);

        userRepository.save(userEntity);
    }
}
