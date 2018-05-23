package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.CommonExceptionType;

public class NotFoundException extends AbstractApplicationRuntimeException {

    public NotFoundException(final String devMessage) {
        super(CommonExceptionType.NOT_FOUND, devMessage);
    }

    public NotFoundException(final Throwable cause) {
        super(CommonExceptionType.NOT_FOUND, cause);
    }

    public NotFoundException(final String devMessage, final Throwable cause) {
        super(CommonExceptionType.NOT_FOUND, devMessage, cause);
    }
}
