package com.dawidkotarba.blog.model.dto.impl;

public class ResponseWrapper<T> {

    private final T response;

    public ResponseWrapper(final T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }
}
