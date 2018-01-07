package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.repository.CacheableUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CacheableUserRepository cacheableUserRepository;

    @Inject
    UserDetailsServiceImpl(final CacheableUserRepository cacheableUserRepository) {
        this.cacheableUserRepository = cacheableUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return Optional.ofNullable(cacheableUserRepository.findByUsername(username)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}