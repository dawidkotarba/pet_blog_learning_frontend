package com.dawidkotarba.blog.exceptions;

import com.dawidkotarba.blog.enums.ExceptionType;
import io.vavr.collection.Seq;
import lombok.Data;

import java.util.UUID;

@Data
public class ExceptionResponse {

    private UUID uuid;
    private ExceptionType exceptionType;
    private String userMessage;
    private String devMessage;
    private Seq<ValidationError> validationErrors;
}
