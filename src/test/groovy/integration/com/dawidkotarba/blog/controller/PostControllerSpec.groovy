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
import spock.lang.Specification

import javax.inject.Inject
import java.sql.Timestamp

import static org.springframework.http.HttpStatus.NOT_FOUND
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
        given:
        def final TEST_VALUE = "test"
        def authorTest = new AuthorEntity()
        def postTest = new PostEntity()
        authorTest.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
            posts = null
        }
        postTest.with {
            subject = TEST_VALUE
            body = TEST_VALUE
            published = new Timestamp(System.currentTimeMillis())
            authors = new HashSet<AuthorEntity>(Arrays.asList(authorTest))
            comments = null
        }
        authorRepository.save(authorTest)
        postRepository.save(postTest)
        when: 'rest posts/subject/{subject} url is hit'
        def response = mockMvc.perform(get('/posts/subject/' + postTest.subject)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post with proper subject returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def post = content[0]
        post != null
        post.subject == postTest.subject
        post.body == postTest.body
        post.published.nano == postTest.published.nanos
        post.authors != null
        post.authors.username == postTest.authors.username
        post.authors.firstname == postTest.authors.firstname
        post.authors.lastname == postTest.authors.lastname
    }

    def "Should show exception information when post is not found"() {
        when: 'rest is hit with not existing post'
        def response = mockMvc.perform(get('/posts/subject/notExistingPost')).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'Exception message is shown'
        response.status == NOT_FOUND.value()
        response.contentType.contains('application/json')

        content.uuid != null
        content.exceptionType == 'NOT_FOUND'
    }
}
