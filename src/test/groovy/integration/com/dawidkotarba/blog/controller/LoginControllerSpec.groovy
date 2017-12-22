package integration.com.dawidkotarba.blog.controller

import groovy.json.JsonBuilder
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class LoginControllerSpec extends Specification {

    @Inject
    MockMvc mockMvc

    def 'User should log in with correct credentials'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    username 'testuser'
                    password 'testuser'
                }
        )

        when: 'rest login url is hit'
        def response = mockMvc.perform(post('/login')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()))
                .andReturn().response

        then: 'response is correct and user logged in'
        response.status == OK.value()
    }

    def 'User should not log in with bad password'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    username 'testuser'
                    password 'pppp'
                }
        )

        when: 'rest login url is hit'
        def response = mockMvc.perform(post('/login')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()))
                .andReturn().response

        then: 'User is not logged in and status unauthorized'
        response.status == UNAUTHORIZED.value()
    }

    def 'Not existing user should not log in'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    username 'notExistingUser'
                    password 'admin'
                }
        )

        when: 'rest login url is hit'
        def response = mockMvc.perform(post('/login')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()))
                .andReturn().response

        then: 'User is not logged in and status unauthorized'
        response.status == UNAUTHORIZED.value()
    }
}
