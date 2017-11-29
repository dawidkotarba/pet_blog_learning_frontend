package com.dawidkotarba.blog.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Dawid Kotarba on 17.11.2015.
 */
@Data
@AllArgsConstructor
public class ValidationError {

    private String fieldName;
    private String message;
}
