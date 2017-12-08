package com.dawidkotarba.blog.converters;

@Deprecated
public interface Converter<E, D> {
    D convertToDto(E entity);

    E convertToEntity(D dto);
}
