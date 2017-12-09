package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.CommonExceptionType;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class InternalErrorException extends AbstractApplicationRuntimeException {

    public InternalErrorException(final String devMessage) {
        super(CommonExceptionType.INTERNAL_ERROR, devMessage);
    }

    public InternalErrorException(final Throwable cause) {
        super(CommonExceptionType.INTERNAL_ERROR, cause);
    }

    public InternalErrorException(final String devMessage, final Throwable cause) {
        super(CommonExceptionType.INTERNAL_ERROR, devMessage, cause);
    }
}
