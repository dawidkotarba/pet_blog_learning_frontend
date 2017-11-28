package com.dawidkotarba.blog.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

/**
 * Created by Dawid Kotarba on 11.02.2016.
 */
@MessageEndpoint
@Slf4j
public class ReportRequestBuilder {

    @Transformer(inputChannel = "requestChannel", outputChannel = "reportingServiceChannel")
    public String createRequestMessage(Message<String> message) {
        log.info("Received message: {}", message);
        return message.getPayload();
    }
}
