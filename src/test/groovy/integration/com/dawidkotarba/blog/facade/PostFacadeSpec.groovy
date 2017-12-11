package integration.com.dawidkotarba.blog.facade

import com.dawidkotarba.blog.auth.service.AuthenticationService
import com.dawidkotarba.blog.facade.PostFacade
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import com.dawidkotarba.blog.model.dto.impl.PostInDto
import com.dawidkotarba.blog.auth.exceptions.NotAuthorizedException
import java.time.LocalDateTime

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class PostFacadeSpec extends Specification {

    @Inject
    PostFacade postFacade

    def 'Should not adding post without authentication'() {
        when:
        postFacade.add(PostInDto.builder().subject('test').body('test').published(LocalDateTime.now()).build())

        then:
        thrown NotAuthorizedException
    }
}
