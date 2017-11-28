package com.dawidkotarba.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Dawid Kotarba on 11.02.2016.
 */
@Configuration
@IntegrationComponentScan("com.dawidkotarba.blog.integration")
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public MessageChannel requestChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel reportingServiceChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel responseChannel() {
        return new DirectChannel();
    }
}
