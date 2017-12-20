package integration.com.dawidkotarba.blog.facade

import com.dawidkotarba.blog.facade.PostFacade
import com.dawidkotarba.blog.model.dto.impl.PostInDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import spock.lang.Specification

import javax.inject.Inject
import java.time.LocalDateTime

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class PostFacadeSpec extends Specification {

    @Inject
    PostFacade postFacade

    def 'Should not adding post without authentication'() {
        when:
        postFacade.add(PostInDto.builder().subject('test').body('test').published(LocalDateTime.now()).build())

        then:
        thrown AuthenticationCredentialsNotFoundException
    }
}
