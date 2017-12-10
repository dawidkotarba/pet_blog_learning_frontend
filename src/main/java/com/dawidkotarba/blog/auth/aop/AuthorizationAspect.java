package com.dawidkotarba.blog.auth.aop;

import com.dawidkotarba.blog.auth.annotations.AuthorizeAuthorities;
import com.dawidkotarba.blog.auth.service.AuthorizationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Method;

@Named
@Aspect
class AuthorizationAspect {

    private final AuthorizationService authorizationService;

    @Inject
    AuthorizationAspect(final AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Before("@annotation(com.dawidkotarba.blog.auth.annotations.AuthorizeAuthorities)")
    void checkAuthorization(final JoinPoint joinPoint) {
        final MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        final Method method = signature.getMethod();
        final AuthorizeAuthorities authorizeAuthoritiesAnnotation = method.getAnnotation(AuthorizeAuthorities.class);
        authorizationService.authorize(authorizeAuthoritiesAnnotation.authorities());
    }
}