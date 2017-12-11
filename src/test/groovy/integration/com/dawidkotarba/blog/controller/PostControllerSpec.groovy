package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.auth.service.LoginService
import com.dawidkotarba.blog.model.entities.impl.AuthorEntity
import com.dawidkotarba.blog.model.entities.impl.PostEntity
import com.dawidkotarba.blog.repository.AuthorRepository
import com.dawidkotarba.blog.repository.PostRepository
import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject
import java.sql.Timestamp

import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@WithMockUser(authorities = "administrate")
class PostControllerSpec extends Specification {

    @Inject
    AuthorRepository authorRepository
    @Inject
    PostRepository postRepository
    @Inject
    MockMvc mockMvc
    @Inject
    LoginService loginService

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
        post.published == postTest.published.toLocalDateTime().withNano(0).toString()
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

    def 'Should add new post'() {
        given:
        def TEST_VALUE = 'test'
        def TEST_PUBLISHED_VALUE = '2017-12-11T08:06:56'
        def TEST_AUTHOR_ID = 1
        def requestBody = '{\n' +
                '  "subject": "' + TEST_VALUE + '",\n' +
                '  "body": "' + TEST_VALUE + '",\n' +
                '  "published": "' + TEST_PUBLISHED_VALUE + '",\n' +
                '  "authors": ' + '[\n' +
                '    {\n' +
                '      "id": ' + TEST_AUTHOR_ID + ',\n' +
                '      "username": "' + TEST_VALUE + '",\n' +
                '      "firstname": "' + TEST_VALUE + '",\n' +
                '      "lastname": "' + TEST_VALUE + '"\n' +
                '    }\n' +
                '  ]' + '\n' +
                '}'

        when: 'rest add post url is hit'
        def response = mockMvc.perform(post('/posts')
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn().response

        then: 'response is correct and new post is saved in db'
        response.status == OK.value()
        def post = postRepository.findBySubject(TEST_VALUE).get(0)
        post != null
        post.subject == TEST_VALUE
        post.body == TEST_VALUE
        post.published.toLocalDateTime().toString() == TEST_PUBLISHED_VALUE
    }
}
