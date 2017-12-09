package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.auth.exceptions.NotAuthorizedException;
import com.dawidkotarba.blog.auth.service.AuthorizationService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Named
class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public void authorize(final UserAuthority... authorities) {
        final Set<String> allowedAuthorities = Arrays.asList(authorities).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        final Set<String> userAuthorities =
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        if (isAuthorized(userAuthorities, allowedAuthorities)) {
            return;
        }
        throw new NotAuthorizedException("Unauthorized");
    }

    private boolean isAuthorized(final Set<String> userAuthorities, final Set<String> allowedAuthorities) {
        for (final String allowedAuthority : allowedAuthorities) {
            if (userAuthorities.contains(allowedAuthority)) {
                return true;
            }
        }
        return false;
    }
}