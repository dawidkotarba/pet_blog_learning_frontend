package com.dawidkotarba.blog.auth.service;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface AuthenticationService {
    Authentication authenticate(String username, String password);

    Set<UserAuthority> getCurrentUserAuthorities();

    boolean hasCurrentUserAuthority(UserAuthority userAuthority);

}