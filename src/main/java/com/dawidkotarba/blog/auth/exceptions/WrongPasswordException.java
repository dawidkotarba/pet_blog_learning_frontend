package com.dawidkotarba.blog.auth.exceptions;

import com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException;

public class WrongPasswordException extends AbstractApplicationRuntimeException {

    public WrongPasswordException(final String message) {
        super(AuthenticationExceptionType.WRONG_PASSWORD, message);
    }

    public WrongPasswordException(final Throwable cause) {
        super(AuthenticationExceptionType.WRONG_PASSWORD, cause);
    }

    public WrongPasswordException(final String message, final Throwable cause) {
        super(AuthenticationExceptionType.WRONG_PASSWORD, message, cause);
    }

}