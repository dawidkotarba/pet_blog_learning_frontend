package com.dawidkotarba.blog.aop.logging;

import com.dawidkotarba.blog.exceptions.InternalErrorException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Named;


/**
 * Created by Dawid Kotarba on 13.11.2015.
 */
@Aspect
@Named
@Slf4j
public class RepositoryLoggerAspect {

    @Around("execution(* com.dawidkotarba.blog.repository.*.*(..))")
    public Object log(final ProceedingJoinPoint pjp) {
        final long start = System.currentTimeMillis();
        final Object output;

        try {
            output = pjp.proceed();
        } catch (final Throwable throwable) {
            throw new InternalErrorException("Cannot proceed in repository logger aspect", throwable.getCause());
        }

        final long elapsedTime = System.currentTimeMillis() - start;
        log.info("Execution time of {}: {} ms", pjp.getSignature(), elapsedTime);

        return output;
    }
}
