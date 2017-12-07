package integration.com.dawidkotarba.blog.controller

import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class PostControllerSpec extends Specification {

    @Inject
    MockMvc mockMvc

    def 'Should return at least one post'() {
        when: 'rest posts url is hit'
        def response = mockMvc.perform(get('/posts')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def post = content[0]
        post != null
    }

    def 'Should return post with proper subject'() {
        when: 'rest posts/{subject} url is hit'
        def response = mockMvc.perform(get('/posts/Test post subject')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post with proper subject returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def returnedPost = content[0]
        returnedPost != null
        returnedPost.subject == 'Test post subject'
    }
}
