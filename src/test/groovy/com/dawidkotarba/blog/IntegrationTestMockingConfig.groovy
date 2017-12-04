package com.dawidkotarba.blog

import org.springframework.boot.test.context.TestConfiguration
import spock.mock.DetachedMockFactory

/**
 * Declare mock beans like that;
 *
 * @Bean Beanname externalRankingService() {
 * factory.Mock(Beanname)
 *}
 */
@TestConfiguration
class IntegrationTestMockingConfig {
    private final DetachedMockFactory factory = new DetachedMockFactory();
}