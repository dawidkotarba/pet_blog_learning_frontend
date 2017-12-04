package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class NotFoundException extends AbstractApplicationRuntimeException {

    public NotFoundException(final String message) {
        super(ExceptionType.NOT_FOUND, message);
    }

    public NotFoundException(final Throwable cause) {
        super(ExceptionType.NOT_FOUND, cause);
    }

    public NotFoundException(final String message, final Throwable cause) {
        super(ExceptionType.NOT_FOUND, message, cause);
    }
}
