package com.dawidkotarba.blog.auth.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

public enum AuthenticationExceptionType implements ExceptionType {
    WRONG_USERNAME, WRONG_PASSWORD
}