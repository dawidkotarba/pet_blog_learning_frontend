package com.dawidkotarba.blog.auth.exceptions;

import com.dawidkotarba.blog.enums.CommonExceptionType;
import com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException;

public class NotAuthorizedException extends AbstractApplicationRuntimeException {

    public NotAuthorizedException(final String message) {
        super(CommonExceptionType.NOT_AUTHORIZED, message);
    }

    public NotAuthorizedException(final Throwable cause) {
        super(CommonExceptionType.NOT_AUTHORIZED, cause);
    }

    public NotAuthorizedException(final String message, final Throwable cause) {
        super(CommonExceptionType.NOT_AUTHORIZED, message, cause);
    }
}