package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.entities.IdentifiableEntity;

import java.io.Serializable;

public interface InConverter<D extends Serializable, E extends IdentifiableEntity> {
    E convert(D dto);
}
