package com.dawidkotarba.blog.service.impl;

import com.dawidkotarba.blog.enums.CommonExceptionType;
import com.dawidkotarba.blog.enums.ExceptionType;
import com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException;
import com.dawidkotarba.blog.exceptions.ExceptionResponse;
import com.dawidkotarba.blog.exceptions.ValidationError;
import com.dawidkotarba.blog.service.impl.i18n.LocalizationService;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Named
public class ExceptionConverterService {

    private final LocalizationService localizationService;

    @Inject
    ExceptionConverterService(final LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    public ExceptionResponse convert(final AbstractApplicationRuntimeException e) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setUuid(e.getUuid());
        exceptionResponse.setExceptionType(e.getExceptionType());
        exceptionResponse.setUserMessage(getLocalizedUserMessage(e.getExceptionType(), e.getParams()));
        exceptionResponse.setDevMessage(e.getMessage());
        return exceptionResponse;
    }

    public ExceptionResponse convert(final Exception e) {
        return convert(e, CommonExceptionType.INTERNAL_ERROR);
    }

    public ExceptionResponse convert(final AuthenticationException e) {
        return convert(e, CommonExceptionType.UNAUTHORIZED);
    }

    public ExceptionResponse convert(final Exception e, final BindingResult bindingResult) {
        final ExceptionResponse exceptionResponse = convert(e, CommonExceptionType.BAD_REQUEST);
        exceptionResponse.setValidationErrors(parseBindingResult(bindingResult));
        return exceptionResponse;
    }

    private ExceptionResponse convert(final Exception e, final CommonExceptionType exceptionType) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setUuid(UUID.randomUUID());
        exceptionResponse.setExceptionType(exceptionType);
        exceptionResponse.setUserMessage(getLocalizedUserMessage(exceptionType));
        exceptionResponse.setDevMessage(e.getMessage());
        return exceptionResponse;
    }

    private String getLocalizedUserMessage(final ExceptionType exceptionType) {
        return getLocalizedUserMessage(exceptionType, null);
    }

    private String getLocalizedUserMessage(final ExceptionType exceptionType, final Seq<String> params) {
        return (params != null) ? localizationService.getMessage(exceptionType.name(), params.toJavaArray())
                : localizationService.getMessage(exceptionType.name());
    }

    private Seq<ValidationError> parseBindingResult(final BindingResult bindingResult) {
        final Collection<ValidationError> validationErrors = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return List.ofAll(validationErrors);
    }
}
