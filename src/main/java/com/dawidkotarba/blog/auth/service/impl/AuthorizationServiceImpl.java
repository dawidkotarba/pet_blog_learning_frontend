package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.auth.exceptions.UnauthorizedException;
import com.dawidkotarba.blog.auth.service.AuthorizationService;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Named;
import java.util.Collection;

@Named
class AuthorizationServiceImpl implements AuthorizationService {

    @Override
    public void authorize(final GrantedAuthority... authorities) {
        final Set<String> allowedAuthorities = Stream.of(authorities)
                .map(GrantedAuthority::getAuthority)
                .toSet();
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            final Set<String> userAuthorities = Stream.ofAll(authentication.getAuthorities())
                    .map(GrantedAuthority::getAuthority)
                    .toSet();
            if (isAuthorized(userAuthorities, allowedAuthorities)) {
                return;
            }
        }
        throw new UnauthorizedException("Unauthorized");
    }

    @Override
    public Set<GrantedAuthority> getCurrentUserAuthorities() {
        final Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        return HashSet.ofAll(authorities);
    }

    @Override
    public boolean hasCurrentUserAuthority(final GrantedAuthority userAuthority) {
        final Set<GrantedAuthority> usersAuthorities = getCurrentUserAuthorities();
        // TODO: 11.05.18  anymatch in vavr
        return usersAuthorities.toJavaSet().stream().anyMatch(role -> role.equals(userAuthority));
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