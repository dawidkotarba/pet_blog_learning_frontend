package com.dawidkotarba.blog.aop;

import com.dawidkotarba.blog.auth.exceptions.UnauthorizedException;
import com.dawidkotarba.blog.exceptions.ExceptionResponse;
import com.dawidkotarba.blog.exceptions.InternalErrorException;
import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.service.impl.ExceptionConverterService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.inject.Inject;

/**
 * This advice catches all exceptions thrown by backend
 * and creates a localized user-readable {@link ExceptionResponse}.
 *
 * @author Dawid Kotarba
 * @see com.dawidkotarba.blog.service.impl.i18n.LocalizationService
 * @see com.dawidkotarba.blog.exceptions.AbstractApplicationRuntimeException
 */
@ControllerAdvice
class ExceptionControllerAdvice {

    private final ExceptionConverterService exceptionConverterService;

    @Inject
    ExceptionControllerAdvice(final ExceptionConverterService exceptionConverterService) {
        this.exceptionConverterService = exceptionConverterService;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ExceptionResponse handleException(final Exception e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = InternalErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ExceptionResponse handleException(final InternalErrorException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    ExceptionResponse handleException(final NotFoundException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ExceptionResponse handleException(final UnauthorizedException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ExceptionResponse handleException(final AccessDeniedException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ExceptionResponse handleException(final AuthenticationException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    ExceptionResponse handleException(final AuthenticationCredentialsNotFoundException e) {
        return exceptionConverterService.convert(e);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ExceptionResponse handleException(final MethodArgumentNotValidException e) {
        return exceptionConverterService.convert(e, e.getBindingResult());
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ExceptionResponse handleException(final BindException e) {
        return exceptionConverterService.convert(e, e.getBindingResult());
    }
}
