package integration.com.dawidkotarba.blog

import integration.com.dawidkotarba.blog.AbstractIntegrationSpec
import org.springframework.web.context.WebApplicationContext

import javax.inject.Inject

class BlogAppSpec extends AbstractIntegrationSpec {

    @Inject
    WebApplicationContext applicationContext

    def "Should boot up without errors"() {
        expect: "web application context exists"
        applicationContext != null
    }
}
