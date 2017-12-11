package com.dawidkotarba.blog.auth.service;

public interface LoginService {
    boolean logIn(String username, String password);

    void logOut();
}