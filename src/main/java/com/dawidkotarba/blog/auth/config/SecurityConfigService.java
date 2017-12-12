package com.dawidkotarba.blog.auth.config;

import com.dawidkotarba.blog.repository.CacheableUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.inject.Named;

@Named
class SecurityConfigService implements UserDetailsService {

    private final CacheableUserRepository cacheableUserRepository;

    @Inject
    SecurityConfigService(final CacheableUserRepository cacheableUserRepository) {
        this.cacheableUserRepository = cacheableUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return cacheableUserRepository.findByUsername(username);
    }
}