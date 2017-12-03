package com.dawidkotarba.blog.model.entities;

import java.io.Serializable;
import java.util.UUID;

public interface IdentifiableEntity extends Serializable {
    Long getId();

    Integer getVersion();

    UUID getUuid();
}
