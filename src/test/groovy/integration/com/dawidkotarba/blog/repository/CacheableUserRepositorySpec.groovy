package integration.com.dawidkotarba.blog.repository

import com.dawidkotarba.blog.model.entities.impl.UserEntity
import com.dawidkotarba.blog.repository.CacheableUserRepository
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class CacheableUserRepositorySpec extends Specification {

    @Inject
    CacheableUserRepository underTest

    def "Should find user by username"() {
        given:
        def final TEST_VALUE = "CacheableUserRepositorySpecVal1"
        def user = createTestUser(TEST_VALUE)
        underTest.save(user)

        when:
        def result = underTest.findByUsername(TEST_VALUE)

        then:
        result == user
    }

    def "Should find a user by example"() {
        given:
        def final TEST_VALUE1 = "CacheableUserRepositorySpecVal2"
        def final TEST_VALUE2 = "CacheableUserRepositorySpecVal3"
        underTest.save(createTestUser(TEST_VALUE1))
        underTest.save(createTestUser(TEST_VALUE2))

        when:
        def example = new UserEntity(username: TEST_VALUE1)
        def result = underTest.findAll(example) as List<UserEntity>

        then:
        result.size() == 1
        result.get(0).username == TEST_VALUE1
    }

    UserEntity createTestUser(final String testValueForFields) {
        def user = new UserEntity()
        user.with {
            username = testValueForFields
            password = testValueForFields
        }
        return user
    }
}