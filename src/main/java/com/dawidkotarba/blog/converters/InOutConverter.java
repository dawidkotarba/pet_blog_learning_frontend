package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.entities.IdentifiableEntity;

import java.io.Serializable;

public interface InOutConverter<E extends IdentifiableEntity, D extends Serializable> {
    E convert(D dto);

    D convert(E entity);
}
