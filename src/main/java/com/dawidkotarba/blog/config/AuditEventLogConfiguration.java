package com.dawidkotarba.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;

/**
 * Created by Dawid Kotarba on 28.12.2015.
 */

@Configuration
@Slf4j
public class AuditEventLogConfiguration implements ApplicationListener<AbstractAuthenticationEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        log.info("Authentication event: " + event);
    }
}
