package com.dawidkotarba.blog.model.entities;

import java.io.Serializable;

/**
 * Created by Dawid Kotarba on 19.12.2015.
 */
public interface HasId<T extends Serializable> extends Serializable {

    T getId();
}
