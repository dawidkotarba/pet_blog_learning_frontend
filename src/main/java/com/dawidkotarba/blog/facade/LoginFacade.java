package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.auth.service.LoginService;
import com.dawidkotarba.blog.model.Credentials;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LoginFacade {

    private final LoginService loginService;

    @Inject
    public LoginFacade(final LoginService loginService) {
        this.loginService = loginService;
    }

    public void login(final Credentials credentials) {
        Preconditions.checkNotNull(credentials.getUsername());
        Preconditions.checkNotNull(credentials.getPassword());

        loginService.logIn(credentials.getUsername(), credentials.getPassword());
    }
}
