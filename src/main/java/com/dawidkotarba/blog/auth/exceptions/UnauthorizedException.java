package com.dawidkotarba.blog.auth.exceptions;

import com.dawidkotarba.blog.enums.CommonExceptionType;
import com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException;

public class UnauthorizedException extends AbstractApplicationRuntimeException {

    public UnauthorizedException(final String message) {
        super(CommonExceptionType.UNAUTHORIZED, message);
    }

    public UnauthorizedException(final Throwable cause) {
        super(CommonExceptionType.UNAUTHORIZED, cause);
    }

    public UnauthorizedException(final String message, final Throwable cause) {
        super(CommonExceptionType.UNAUTHORIZED, message, cause);
    }
}