package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.auth.enums.UserAuthority
import com.dawidkotarba.blog.enums.CommonExceptionType
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

    final def TEST_VALUE = 'dummy_integration_value'
    final def TEST_VALUE_ADD = 'dummy_integration_value xxx'
    final def NON_EXISTING_VALUE = 'dummy_non_existing_value'
    final def TEST_PUBLISHED_VALUE = '3017-12-11T08:06:56'

    @Inject
    CacheableAuthorRepository authorRepository
    @Inject
    CacheablePostRepository postRepository
    @Inject
    MockMvc mockMvc
    PostEntity testPost
    AuthorEntity testAuthor

    def setup() {
        testAuthor = new AuthorEntity()
        testAuthor.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
            posts = null
        }
        testPost = new PostEntity()
        testPost.with {
            subject = TEST_VALUE
            body = TEST_VALUE
            published = LocalDateTime.now()
            authors = new HashSet<AuthorEntity>(Arrays.asList(testAuthor))
            comments = null
        }
        authorRepository.saveAndFlush(testAuthor)
        postRepository.saveAndFlush(testPost)
    }

    def cleanup() {
        authorRepository.deleteById(testAuthor.getId())
        postRepository.deleteById(testPost.getId())
    }

    def 'Should return at least one post in pageable format'() {
        when: 'rest posts url is hit'
        def response = mockMvc.perform(get('/api/posts')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.content.size > 0
    }

    def 'Should return at least one post'() {
        when: 'rest posts url is hit'
        def response = mockMvc.perform(get('/api/posts/all')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.size > 0
    }

    def 'Should return post with proper subject'() {
        when: 'rest posts/subject/{subject} url is hit'
        def response = mockMvc.perform(get('/api/posts/subject/' + testPost.subject)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post with proper subject returned'
        response.status == OK.value()
        response.contentType.contains('application/json')
        comparePostFromResponseToTestData(content, testPost)
    }

    def "Should show exception information when post is not found"() {
        when: 'rest is hit with not existing post'
        def response = mockMvc.perform(get('/api/posts/subject/' + NON_EXISTING_VALUE)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'Exception message is shown'
        response.status == NOT_FOUND.value()
        response.contentType.contains('application/json')

        content.uuid != null
        content.exceptionType == CommonExceptionType.NOT_FOUND.toString()
    }

    def 'Should add new post for authenticated user'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    subject TEST_VALUE_ADD
                    body TEST_VALUE_ADD
                    published TEST_PUBLISHED_VALUE
                    authors([testAuthor.getId()])
                }
        )

        when: 'rest add post url is hit'
        def response = mockMvc.perform(post('/api/posts')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()).with((user("testuser").authorities([UserAuthority.WRITE]))))
                .andReturn().response

        then: 'response is correct and new post is saved in db'
        response.status == OK.value()
        def post = postRepository.findBySubject(TEST_VALUE_ADD)
        post != null
        post.subject == TEST_VALUE_ADD
        post.body == TEST_VALUE_ADD
        post.published.toString() == TEST_PUBLISHED_VALUE

        cleanup:
        postRepository.deleteById(post.getId())
    }

    def 'Should not add new post for user without sufficient privileges'() {
        given:
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    subject TEST_VALUE_ADD
                    body TEST_VALUE_ADD
                    published TEST_PUBLISHED_VALUE
                    authors([testAuthor.getId()])
                }
        )

        when: 'rest add post url is hit'
        def response = mockMvc.perform(post('/api/posts')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString()).with((user("testuser").authorities([UserAuthority.READ]))))
                .andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'there was unauthorized status of the response'
        response.status == UNAUTHORIZED.value()
        content.uuid != null
        content.exceptionType == CommonExceptionType.INTERNAL_ERROR.toString()
    }

    def 'Should find two posts within a specified month/year'() {
        given:
        def postTest1 = new PostEntity()
        postTest1.with {
            subject = 'startingPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-12-01T00:00:01')
            authors = new HashSet<AuthorEntity>(Arrays.asList(testAuthor))
            comments = null
        }

        def postTest2 = new PostEntity()
        postTest2.with {
            subject = 'endingPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-12-31T23:59:59')
            authors = new HashSet<AuthorEntity>(Arrays.asList(testAuthor))
            comments = null
        }

        def postTest3 = new PostEntity()
        postTest3.with {
            subject = 'previousMonthPost'
            body = TEST_VALUE
            published = LocalDateTime.parse('3017-11-30T23:59:59')
            authors = new HashSet<AuthorEntity>(Arrays.asList(testAuthor))
            comments = null
        }

        postRepository.saveAndFlush(postTest1)
        postRepository.saveAndFlush(postTest2)
        postRepository.saveAndFlush(postTest3)

        when: 'rest posts/search/ url is hit'
        def response = mockMvc.perform(get('/api/posts/search/3017-12-15')).andReturn().response
        def page = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and contains two posts'
        response.status == OK.value()
        response.contentType.contains('application/json')

        page.size == 2

        cleanup:
        postRepository.deleteById(postTest1.getId())
        postRepository.deleteById(postTest2.getId())
        postRepository.deleteById(postTest3.getId())
    }

    def 'Should return a post by given id'() {
        when: 'rest post url with given id is hit'
        def response = mockMvc.perform(get('/api/posts/' + testPost.id)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and post returned'
        response.status == OK.value()
        response.contentType.contains('application/json')
        comparePostFromResponseToTestData(content, testPost)
    }

    void comparePostFromResponseToTestData(postFromResponse, testData) {
        assert postFromResponse.id == testData.id
        assert postFromResponse.subject == testData.subject
        assert postFromResponse.body == testData.body
        assert postFromResponse.published != null
        assert postFromResponse.authors != null
        assert postFromResponse.authors.username == testData.authors.username
        assert postFromResponse.authors.firstname == testData.authors.firstname
        assert postFromResponse.authors.lastname == testData.authors.lastname
    }
}
