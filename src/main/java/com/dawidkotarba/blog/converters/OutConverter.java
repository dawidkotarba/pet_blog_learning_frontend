package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.entities.IdentifiableEntity;

import java.io.Serializable;

public interface OutConverter<E extends IdentifiableEntity, D extends Serializable> {
    D convert(E entity);
}
