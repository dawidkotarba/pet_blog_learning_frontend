package integration.com.dawidkotarba.blog.controller

import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class UserControllerSpec extends Specification {

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
}
