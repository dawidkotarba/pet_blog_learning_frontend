package com.dawidkotarba.blog.integration;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
public class ResponseWrapper<T> {

    private T response;

    public ResponseWrapper(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }
}
