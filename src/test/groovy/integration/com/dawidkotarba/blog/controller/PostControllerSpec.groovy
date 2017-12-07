package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.model.entities.AuthorEntity
import com.dawidkotarba.blog.model.entities.PostEntity
import com.dawidkotarba.blog.repository.AuthorRepository
import com.dawidkotarba.blog.repository.PostRepository
import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class PostControllerSpec extends Specification {

    @Inject
    AuthorRepository authorRepository
    @Inject
    PostRepository postRepository
    @Inject
    MockMvc mockMvc
    def final TEST_VALUE = "test"
    @Shared
    def authorTest = new AuthorEntity()
    @Shared
    def postTest = new PostEntity()

    def setup() {
        given:
        authorTest.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
        }
        postTest.with {
            subject = TEST_VALUE
            body = TEST_VALUE
            published = new Date()
            author = authorTest
            comments = null
        }
        authorRepository.save(authorTest)
        postRepository.save(postTest)
    }

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
        def response = mockMvc.perform(get('/posts/' + TEST_VALUE)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post with proper subject returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def post = content[0]
        post != null
        post.subject == postTest.subject
        post.body == postTest.body
    }
}
