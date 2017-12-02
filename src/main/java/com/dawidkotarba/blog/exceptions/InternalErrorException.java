package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class InternalErrorException extends AbstractApplicationRuntimeException {

    public InternalErrorException(final String message) {
        super(ExceptionType.INTERNAL_ERROR, message);
    }

    public InternalErrorException(final Throwable cause) {
        super(ExceptionType.INTERNAL_ERROR, cause);
    }

    public InternalErrorException(final String message, final Throwable cause) {
        super(ExceptionType.INTERNAL_ERROR, message, cause);
    }
}
