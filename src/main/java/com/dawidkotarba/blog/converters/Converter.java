package com.dawidkotarba.blog.converters;

public interface Converter<E, D> {
    D convertToDto(E entity);

    E convertToEntity(D dto);
}
