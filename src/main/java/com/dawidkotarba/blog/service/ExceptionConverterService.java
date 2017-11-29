package com.dawidkotarba.blog.service;

import com.dawidkotarba.blog.enums.ExceptionType;
import com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException;
import com.dawidkotarba.blog.exceptions.ExceptionResponse;
import com.dawidkotarba.blog.exceptions.ValidationError;
import com.dawidkotarba.blog.service.i18n.LocalizationService;
import org.springframework.validation.BindingResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dawid Kotarba on 14.11.2015.
 */

@Named
public class ExceptionConverterService {

    private final LocalizationService localizationService;

    @Inject
    public ExceptionConverterService(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    public ExceptionResponse convert(AbstractApplicationRuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setUuid(e.getUuid());
        exceptionResponse.setExceptionType(e.getExceptionType());
        exceptionResponse.setUserMessage(getLocalizedUserMessage(e.getExceptionType(), e.getParams()));
        exceptionResponse.setDevMessage(e.getMessage());

        return exceptionResponse;
    }

    public ExceptionResponse convert(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setUuid(UUID.randomUUID());
        exceptionResponse.setExceptionType(ExceptionType.INTERNAL_ERROR);
        exceptionResponse.setUserMessage(getLocalizedUserMessage(ExceptionType.INTERNAL_ERROR));
        exceptionResponse.setDevMessage(e.getMessage());

        return exceptionResponse;
    }

    public ExceptionResponse convert(Exception e, BindingResult bindingResult) {
        ExceptionResponse exceptionResponse = convert(e);
        exceptionResponse.getValidationErrors().addAll(parseBindingResult(bindingResult));
        return exceptionResponse;
    }

    private String getLocalizedUserMessage(ExceptionType exceptionType) {
        return getLocalizedUserMessage(exceptionType, null);
    }

    private String getLocalizedUserMessage(ExceptionType exceptionType, String[] params) {
        return (params != null) ? localizationService.getMessage(exceptionType.name(), params) : localizationService.getMessage(exceptionType.name());
    }

    private List<ValidationError> parseBindingResult(BindingResult bindingResult) {
        List<ValidationError> validationErrors = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError ->
                validationErrors.add(new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
        );

        return validationErrors;
    }
}
