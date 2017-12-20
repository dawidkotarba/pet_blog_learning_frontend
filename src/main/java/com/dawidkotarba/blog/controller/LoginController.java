package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.LoginFacade;
import com.dawidkotarba.blog.model.Credentials;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "adminPanel/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    private final LoginFacade loginFacade;

    @Inject
    public LoginController(final LoginFacade loginFacade) {
        this.loginFacade = loginFacade;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void login(@RequestBody final Credentials credentials) {
        loginFacade.login(credentials);
    }
}
