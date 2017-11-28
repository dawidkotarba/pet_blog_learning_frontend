package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class InternalErrorException extends AbstractApplicationRuntimeException {

    public InternalErrorException(String message) {
        super(ExceptionType.INTERNAL_ERROR, message);
    }

    public InternalErrorException(Throwable cause) {
        super(ExceptionType.INTERNAL_ERROR, cause);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(ExceptionType.INTERNAL_ERROR, message, cause);
    }
}
