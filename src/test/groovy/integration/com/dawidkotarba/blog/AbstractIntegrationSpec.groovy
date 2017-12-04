package integration.com.dawidkotarba.blog

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
abstract class AbstractIntegrationSpec extends Specification {
}
