package com.dawidkotarba.blog.auth.service;

import com.dawidkotarba.blog.auth.enums.UserAuthority;

import java.util.Set;

public interface AuthorizationService {
    void authorize(UserAuthority... authorizedRoles);

    Set<UserAuthority> getCurrentUserAuthorities();

    boolean hasCurrentUserAuthority(UserAuthority userAuthority);
}