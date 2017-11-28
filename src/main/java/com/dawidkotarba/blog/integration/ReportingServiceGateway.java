package com.dawidkotarba.blog.integration;

import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by Dawid Kotarba on 11.02.2016.
 */
@MessagingGateway(name = "reportingServiceGateway", defaultRequestChannel = "requestChannel")
public interface ReportingServiceGateway {
    String generateReport(String country);
}
