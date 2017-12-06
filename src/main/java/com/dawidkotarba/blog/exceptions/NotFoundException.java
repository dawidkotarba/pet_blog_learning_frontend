package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class NotFoundException extends AbstractApplicationRuntimeException {

    public NotFoundException(final String devMessage) {
        super(ExceptionType.NOT_FOUND, devMessage);
    }

    public NotFoundException(final Throwable cause) {
        super(ExceptionType.NOT_FOUND, cause);
    }

    public NotFoundException(final String devMessage, final Throwable cause) {
        super(ExceptionType.NOT_FOUND, devMessage, cause);
    }
}
