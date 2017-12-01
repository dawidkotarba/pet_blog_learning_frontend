package com.dawidkotarba.blog.aop.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.inject.Named;

/**
 * Created by Dawid Kotarba on 20.11.2015.
 */

@Aspect
@Named
@Slf4j
class ExceptionLoggerAspect {

    @Pointcut("within(com.dawidkotarba.blog.dao..*)"
            + "|| within(com.dawidkotarba.blog.service..*)"
            + "|| within(com.dawidkotarba.blog.controllers..*)")
    void loggingPointcut() {
        // intentionally left blank
    }

    @AfterThrowing(pointcut = "loggingPointcut()", throwing = "e")
    void logException(final JoinPoint joinPoint, final Throwable e) {
        log.error("Exception in {}.{}() with cause = {} and exception {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause(), e);
    }

}
