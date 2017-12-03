package com.dawidkotarba.blog

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@Import([IntegrationTestMockingConfig])
abstract class AbstractIntegrationSpec extends Specification {
}
