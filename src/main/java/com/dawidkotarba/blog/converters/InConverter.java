package com.dawidkotarba.blog.converters;

import com.dawidkotarba.blog.model.dto.InDto;
import com.dawidkotarba.blog.model.entities.IdentifiableEntity;

public interface InConverter<D extends InDto, E extends IdentifiableEntity> {
    E convert(D dto);
}
