package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.auth.exceptions.NotAuthorizedException;
import com.dawidkotarba.blog.auth.service.AuthorizationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Named
class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public void authorize(final UserAuthority... authorities) {
        final Set<String> allowedAuthorities = Arrays.asList(authorities).stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final Set<String> userAuthorities =
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
            if (isAuthorized(userAuthorities, allowedAuthorities)) {
                return;
            }
        }
        throw new NotAuthorizedException("Unauthorized");
    }

    @Override
    public Set<UserAuthority> getCurrentUserAuthorities() {
        final Collection<UserAuthority> authorities = (Collection<UserAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return new HashSet<>(authorities);
    }

    @Override
    public boolean hasCurrentUserAuthority(final UserAuthority userAuthority) {
        final Set<UserAuthority> usersAuthorities = getCurrentUserAuthorities();
        return usersAuthorities.stream().anyMatch(role -> role.equals(userAuthority));
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