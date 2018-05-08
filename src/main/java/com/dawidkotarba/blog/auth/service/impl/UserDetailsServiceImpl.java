package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.repository.CacheableUserRepository;
import io.vavr.control.Option;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CacheableUserRepository cacheableUserRepository;

    @Inject
    UserDetailsServiceImpl(final CacheableUserRepository cacheableUserRepository) {
        this.cacheableUserRepository = cacheableUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return Option.of(cacheableUserRepository.findByUsername(username)).getOrElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}