package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.model.entities.impl.UserEntity
import com.dawidkotarba.blog.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class UserControllerSpec extends Specification {

    @Inject
    UserRepository userRepository
    @Inject
    MockMvc mockMvc

    def 'Should return at least one user'() {
        when: 'rest users url is hit'
        def response = mockMvc.perform(get('/users')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and user returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def user = content[0]
        user != null
    }

    def 'Should return admin user'() {
        when: 'rest single user url is hit'
        def response = mockMvc.perform(get('/users/admin')).andReturn().response
        def user = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and admin user returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        user != null
        user.username == 'admin'
        user.enabled == true
        user.role == 'role_admin'
    }

    def "Should show exception information when user is not found"() {
        when: 'rest is hit with not existing user'
        def response = mockMvc.perform(get('/users/nosuchuser')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'Exception message is shown'
        response.status == NOT_FOUND.value()
        response.contentType.contains('application/json')

        content.uuid != null
        content.exceptionType == 'NOT_FOUND'
    }

    def 'Should add new user'() {
        given:
        def TEST_VALUE = 'test'
        def objectMapper = new ObjectMapper()
        def newUser = new UserEntity()
        newUser.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
            password = TEST_VALUE
            enabled = true
            role = TEST_VALUE
        }
        def requestBody = objectMapper.writeValueAsString(newUser)

        when: 'rest add user url is hit'
        def response = mockMvc.perform(post('/users')
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn().response

        then: 'response is correct and new user is saved in db'
        response.status == OK.value()
        def user = userRepository.findByUsername(TEST_VALUE)
        user.username == newUser.username
        user.firstname == newUser.firstname
        user.lastname == newUser.lastname
        user.password == newUser.password
        user.enabled == newUser.enabled
        user.role == newUser.role
    }
}
