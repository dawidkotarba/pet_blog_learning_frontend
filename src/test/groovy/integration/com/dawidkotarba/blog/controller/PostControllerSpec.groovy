package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.auth.enums.UserAuthority
import com.dawidkotarba.blog.model.entities.impl.AuthorEntity
import com.dawidkotarba.blog.model.entities.impl.PostEntity
import com.dawidkotarba.blog.repository.CacheableAuthorRepository
import com.dawidkotarba.blog.repository.CacheablePostRepository
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject
import java.time.LocalDateTime

import static org.springframework.http.HttpStatus.*
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class PostControllerSpec extends Specification {

    @Inject
    CacheableAuthorRepository authorRepository
    @Inject
    CacheablePostRepository cacheablePostRepository
    @Inject
    MockMvc mockMvc

    AuthorEntity authorTest

    @Shared
    PostEntity postTest
    def TEST_VALUE = 'dummy_integration_value'
    def TEST_VALUE_ADD = 'dummy_integration_value xxx'
    def NON_EXISTING_VALUE = 'dummy_non_existing_value'
    def TEST_PUBLISHED_VALUE = '3017-12-11T08:06:56'

    def setup() {
        println('Setting up test data')

        authorTest = new AuthorEntity()
        authorTest.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
            posts = null
        }
        postTest = new PostEntity()
        postTest.with {
            subject = TEST_VALUE
            body = TEST_VALUE
            published = LocalDateTime.now()
            authors = new HashSet<AuthorEntity>(Arrays.asList(authorTest))
            comments = null
        }
        authorRepository.saveAndFlush(authorTest)
        cacheablePostRepository.saveAndFlush(postTest)
    }

    def cleanup() {
        println('Cleaning up after a test!')
        authorRepository.delete(authorTest.getId())
        cacheablePostRepository.delete(postTest.getId())
    }

    def 'Should return at least one post in pageable format'() {
        when: 'rest posts url is hit'
        def response = mockMvc.perform(get('/posts')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.content.size > 0
    }

    def 'Should return at least one post'() {
        when: 'rest posts url is hit'
        def response = mockMvc.perform(get('/posts/all')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.size > 0
    }

    def 'Should return post with proper subject'() {
        when: 'rest posts/subject/{subject} url is hit'
        def response = mockMvc.perform(get('/posts/subject/' + postTest.subject)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post with proper subject returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        def post = content
        post != null
        post.subject == postTest.subject
        post.body == postTest.body
        post.published == postTest.published.withNano(0).toString()
        post.authors != null
        post.authors.username == postTest.authors.username
        post.authors.firstname == postTest.authors.firstname
        post.authors.lastname == postTest.authors.lastname
    }

    def "Should show exception information when post is not found"() {
        when: 'rest is hit with not existing post'
        def response = mockMvc.perform(get('/posts/subject/' + NON_EXISTING_VALUE)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'Exception message is shown'
        response.status == NOT_FOUND.value()
        response.contentType.contains('application/json')

        content.uuid != null
        content.exceptionType == 'NOT_FOUND'
    }

    def 'Should add new post for authenticated user'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    subject TEST_VALUE_ADD
                    body TEST_VALUE_ADD
                    published TEST_PUBLISHED_VALUE
                    authors([authorTest.getId()])
                }
        )

        when: 'rest add post url is hit'
        def response = mockMvc.perform(post('/posts')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()).with((user("testuser").authorities([UserAuthority.WRITE]))))
                .andReturn().response

        then: 'response is correct and new post is saved in db'
        response.status == OK.value()
        def post = cacheablePostRepository.findBySubject(TEST_VALUE_ADD)
        post != null
        post.subject == TEST_VALUE_ADD
        post.body == TEST_VALUE_ADD
        post.published.toString() == TEST_PUBLISHED_VALUE

        cleanup:
        cacheablePostRepository.delete(post.getId())
    }

    def 'Should not add new post for user without sufficient privileges'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    subject TEST_VALUE_ADD
                    body TEST_VALUE_ADD
                    published TEST_PUBLISHED_VALUE
                    authors([authorTest.getId()])
                }
        )

        when: 'rest add post url is hit'
        def response = mockMvc.perform(post('/posts')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()).with((user("testuser").authorities([UserAuthority.READ]))))
                .andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'there was unauthorized status of the response'
        response.status == UNAUTHORIZED.value()
        content.uuid != null
        content.exceptionType == 'UNAUTHORIZED'
    }

    def 'Should find two posts within a specified month/year'() {
        given:
        def postTest1 = new PostEntity()
        postTest1.with {
            subject = 'startingPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-12-01T00:00:01')
            authors = new HashSet<AuthorEntity>(Arrays.asList(authorTest))
            comments = null
        }

        def postTest2 = new PostEntity()
        postTest2.with {
            subject = 'endingPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-12-31T23:59:59')
            authors = new HashSet<AuthorEntity>(Arrays.asList(authorTest))
            comments = null
        }

        def postTest3 = new PostEntity()
        postTest3.with {
            subject = 'previousMonthPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-11-30T23:59:59')
            authors = new HashSet<AuthorEntity>(Arrays.asList(authorTest))
            comments = null
        }

        cacheablePostRepository.saveAndFlush(postTest1)
        cacheablePostRepository.saveAndFlush(postTest2)
        cacheablePostRepository.saveAndFlush(postTest3)

        when: 'rest posts/search/ url is hit'
        def response = mockMvc.perform(get('/posts/search/3017-12-15')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and contains two posts'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.size == 2

        cleanup:
        cacheablePostRepository.delete(postTest1.getId())
        cacheablePostRepository.delete(postTest2.getId())
        cacheablePostRepository.delete(postTest3.getId())
    }
}
