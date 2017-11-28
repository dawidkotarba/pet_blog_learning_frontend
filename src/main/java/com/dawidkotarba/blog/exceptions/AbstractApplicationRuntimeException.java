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

    public AbstractApplicationRuntimeException(ExceptionType exceptionType, String message) {
        super(message);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException(ExceptionType exceptionType, Throwable cause) {
        super(cause);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException(ExceptionType exceptionType, String message, Throwable cause) {
        super(message, cause);
        init(exceptionType);
    }

    public AbstractApplicationRuntimeException addParams(String... params) {
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

    private void init(ExceptionType exceptionType) {
        uuid = UUID.randomUUID();
        this.exceptionType = exceptionType;
    }
}
