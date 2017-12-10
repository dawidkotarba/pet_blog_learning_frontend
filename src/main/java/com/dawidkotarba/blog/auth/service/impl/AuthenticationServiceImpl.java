package com.dawidkotarba.blog.auth.service.impl;

import com.dawidkotarba.blog.auth.service.AuthenticationService;
import com.google.common.base.Preconditions;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import javax.inject.Inject;
import javax.inject.Named;

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
}