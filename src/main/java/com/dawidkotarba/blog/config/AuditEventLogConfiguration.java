package com.dawidkotarba.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;

@Configuration
@Slf4j
class AuditEventLogConfiguration implements ApplicationListener<AbstractAuthenticationEvent> {

    @Override
    public void onApplicationEvent(final AbstractAuthenticationEvent event) {
        log.info("Authentication event: " + event);
    }
}
