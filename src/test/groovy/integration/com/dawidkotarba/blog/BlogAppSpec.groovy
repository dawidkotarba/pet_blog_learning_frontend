package integration.com.dawidkotarba.blog

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class BlogAppSpec extends Specification {

    @Inject
    WebApplicationContext applicationContext

    def "Should boot up without errors"() {
        expect: "web application context exists"
        applicationContext != null
    }
}
