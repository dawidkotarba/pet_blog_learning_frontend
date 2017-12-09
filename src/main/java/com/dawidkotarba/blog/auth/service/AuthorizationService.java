package com.dawidkotarba.blog.auth.service;

import com.dawidkotarba.blog.auth.enums.UserAuthority;

public interface AuthorizationService {
    void authorize(UserAuthority... authorizedRoles);
}