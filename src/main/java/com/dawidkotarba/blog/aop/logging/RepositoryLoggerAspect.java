package com.dawidkotarba.blog.aop.logging;

import com.dawidkotarba.blog.exceptions.InternalErrorException;
import com.google.common.base.Throwables;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Named;

@Aspect
@Named
@Slf4j
class RepositoryLoggerAspect {

    @Around("execution(* com.dawidkotarba.blog.repository.*.*(..))")
    Object log(final ProceedingJoinPoint pjp) {
        final long start = System.currentTimeMillis();
        final Object output = Try.of(pjp::proceed).getOrElseThrow(throwable -> {
            log.error(Throwables.getStackTraceAsString(throwable));
            return new InternalErrorException(Throwables.getRootCause(throwable));
        });
        final long elapsedTime = System.currentTimeMillis() - start;
        log.info("Execution time of {}: {} ms", pjp.getSignature(), elapsedTime);
        return output;
    }
}
