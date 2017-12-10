package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.auth.service.AuthenticationService;
import com.google.common.base.Preconditions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Named
class AuthenticationServiceImpl implements AuthenticationService {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    @Inject
    AuthenticationServiceImpl(final DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    @Override
    public Authentication authenticate(final String username, final String password) {
        Preconditions.checkNotNull(username);
        Preconditions.checkNotNull(password);

        return daoAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(username, password));
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
}