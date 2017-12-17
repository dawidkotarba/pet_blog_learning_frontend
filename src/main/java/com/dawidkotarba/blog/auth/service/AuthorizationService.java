package com.dawidkotarba.blog.auth.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public interface AuthorizationService {
    void authorize(GrantedAuthority... authorizedRoles);

    Set<GrantedAuthority> getCurrentUserAuthorities();

    boolean hasCurrentUserAuthority(GrantedAuthority userAuthority);
}