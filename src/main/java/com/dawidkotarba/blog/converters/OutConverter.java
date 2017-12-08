package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.OutDto;
import com.dawidkotarba.blog.model.entities.IdentifiableEntity;

public interface OutConverter<E extends IdentifiableEntity, D extends OutDto> {
    D convert(E entity);
}
