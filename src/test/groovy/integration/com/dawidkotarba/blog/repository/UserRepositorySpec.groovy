package integration.com.dawidkotarba.blog.repository

import com.dawidkotarba.blog.model.entities.impl.UserEntity
import com.dawidkotarba.blog.repository.UserRepository
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@DataJpaTest
class UserRepositorySpec extends Specification {

    @Inject
    UserRepository userRepository

    def "Should find user by username"() {
        given:
        def user = new UserEntity()
        def final TEST_VALUE = "test"
        user.with {
            username = TEST_VALUE
            password = TEST_VALUE
            enabled = true
            role = TEST_VALUE
        }
        userRepository.save(user)

        when:
        def result = userRepository.findByUsername(TEST_VALUE)

        then:
        result == user
    }
}