package integration.com.dawidkotarba.blog.facade

import com.dawidkotarba.blog.facade.UserFacade
import com.dawidkotarba.blog.model.dto.impl.UserInDto
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class UserFacadeSpec extends Specification {

    @Inject
    UserFacade userFacade

    def 'Should not adding post without authentication'() {
        when:
        userFacade.add(UserInDto.builder()
                .username('test')
                .firstname('test')
                .lastname('test')
                .build())

        then:
        thrown AuthenticationCredentialsNotFoundException
    }
}
