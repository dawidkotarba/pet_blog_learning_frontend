package com.dawidkotarba.blog.dto;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class ResponseWrapper<T> {

    private final T response;

    public ResponseWrapper(final T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }
}
