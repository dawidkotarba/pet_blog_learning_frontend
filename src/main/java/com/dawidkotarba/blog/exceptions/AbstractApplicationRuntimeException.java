package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

import java.util.UUID;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public abstract class AbstractApplicationRuntimeException extends RuntimeException {

    private UUID uuid;
    private String[] params;
    private ExceptionType exceptionType;

    public AbstractApplicationRuntimeException(final ExceptionType exceptionType, final String message) {
        super(message);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException(final ExceptionType exceptionType, final Throwable cause) {
        super(cause);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException(final ExceptionType exceptionType, final String message, final Throwable cause) {
        super(message, cause);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException addParams(final String... params) {
        this.params = params;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String[] getParams() {
        return params;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    private void init(final ExceptionType exceptionType) {
        uuid = UUID.randomUUID();
        this.exceptionType = exceptionType;
    }
}
