package com.dawidkotarba.blog.repository

import com.dawidkotarba.blog.AbstractIntegrationSpec
import com.dawidkotarba.blog.model.entities.UserEntity
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

import javax.inject.Inject

@DataJpaTest
class UserRepositorySpec extends AbstractIntegrationSpec {

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