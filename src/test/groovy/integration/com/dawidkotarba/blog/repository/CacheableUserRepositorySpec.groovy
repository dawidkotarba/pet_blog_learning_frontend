package integration.com.dawidkotarba.blog.repository

import com.dawidkotarba.blog.model.entities.impl.UserEntity
import com.dawidkotarba.blog.repository.CacheableUserRepository
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
class CacheableUserRepositorySpec extends Specification {

    def final TEST_VALUE1 = "test1"
    def final TEST_VALUE2 = "test2"
    def final TEST_VALUE3 = "test3"

    @Inject
    CacheableUserRepository underTest

    def setup() {
        underTest.save(createTestUser(TEST_VALUE1, true))
        underTest.save(createTestUser(TEST_VALUE2, true))
        underTest.save(createTestUser(TEST_VALUE3, false))
    }

    def cleanup(){
        underTest.deleteAll()
    }

    def "Should find user by username"() {
        when:
        def result = underTest.findByUsername(TEST_VALUE1)

        then:
        result.username == TEST_VALUE1
    }

    def "Should find a user by example"() {
        when:
        def example = new UserEntity(enabled: false)
        def result = underTest.findAll(example) as List<UserEntity>

        then:
        result.size() == 1
        result.get(0).username == TEST_VALUE3
    }

    def "Should find list of enabled users"() {
        when:
        def example = new UserEntity(enabled: true)
        def result = underTest.findAll(example) as List<UserEntity>

        then:
        result.size() == 2
        result.get(0).username == TEST_VALUE1
        result.get(1).username == TEST_VALUE2
    }

    def "Should count two users"() {
        when:
        def example = new UserEntity(enabled: true)
        def count = underTest.count(example)

        then:
        count == 2
    }

    def "Should find one matching user"() {
        when:
        def example = new UserEntity(enabled: false)
        def result = underTest.findAll(example) as List<UserEntity>

        then:
        result.size() == 1
        result.get(0).username == TEST_VALUE3
    }

    UserEntity createTestUser(final String testValueForFields, final boolean isEnabled = false) {
        def user = new UserEntity()
        user.with {
            username = testValueForFields
            password = testValueForFields
            enabled = isEnabled
        }
        return user
    }
}