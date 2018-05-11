package com.dawidkotarba.blog.auth.service;

import io.vavr.collection.Set;
import org.springframework.security.core.GrantedAuthority;

public interface AuthorizationService {
    void authorize(GrantedAuthority... authorizedRoles);

    Set<GrantedAuthority> getCurrentUserAuthorities();

    boolean hasCurrentUserAuthority(GrantedAuthority userAuthority);
}