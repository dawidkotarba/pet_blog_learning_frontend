package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public abstract class AbstractApplicationRuntimeException extends RuntimeException {

    private UUID uuid;
    private List<String> params;
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

    public AbstractApplicationRuntimeException addParams(final List<String> params) {
        this.params = params;
        return this;
    }

    public UUID getUuid() {
        return uuid;
    }

    public List<String> getParams() {
        return params != null ? Collections.unmodifiableList(params) : Collections.emptyList();
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    private void init(final ExceptionType exceptionType) {
        uuid = UUID.randomUUID();
        this.exceptionType = exceptionType;
    }
}
