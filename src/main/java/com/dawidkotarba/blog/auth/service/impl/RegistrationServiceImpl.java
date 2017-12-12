package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.model.entities.impl.UserEntity;
import com.dawidkotarba.blog.repository.CacheableUserRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class RegistrationServiceImpl {

    private final CacheableUserRepository cacheableUserRepository;

    @Inject
    public RegistrationServiceImpl(final CacheableUserRepository cacheableUserRepository) {
        this.cacheableUserRepository = cacheableUserRepository;
    }

    public void registerUser(final UserEntity userEntity) {
        Preconditions.checkNotNull(userEntity);
        Preconditions.checkNotNull(userEntity.getAuthorities());

        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);

        cacheableUserRepository.save(userEntity);
    }
}
