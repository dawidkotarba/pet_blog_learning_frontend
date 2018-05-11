package integration.com.dawidkotarba.blog.facade

import com.dawidkotarba.blog.facade.PostFacade
import com.dawidkotarba.blog.model.dto.impl.PostInDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class PostFacadeSpec extends Specification {

    @Inject
    PostFacade postFacade

    def 'Should not adding post without authentication'() {
        given:
        def testProperty = 'test'
        def post = new PostInDto()
        post.with {
            subject = testProperty
            body = testProperty
        }

        when:
        postFacade.add(post)

        then:
        thrown AuthenticationCredentialsNotFoundException
    }
}
