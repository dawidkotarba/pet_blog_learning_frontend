package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity
import com.dawidkotarba.blog.repository.CacheableAuthorRepository
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
class AuthorControllerSpec extends Specification {

    final def TEST_VALUE = 'dummy_integration_value'
    final def TEST_VALUE2 = 'dummy2_integration_value'
    final def QUERY_DUMMY = 'dummy'

    @Inject
    CacheableAuthorRepository authorRepository
    @Inject
    MockMvc mockMvc
    AuthorEntity testAuthor
    AuthorEntity testAuthor2


    def setup() {
        testAuthor = new AuthorEntity()
        testAuthor.with {
            username = TEST_VALUE
            firstname = TEST_VALUE
            lastname = TEST_VALUE
            posts = null
        }
        testAuthor2 = new AuthorEntity()
        testAuthor2.with {
            username = TEST_VALUE2
            firstname = TEST_VALUE2
            lastname = TEST_VALUE2
            posts = null
        }
        authorRepository.saveAndFlush(testAuthor)
        authorRepository.saveAndFlush(testAuthor2)
    }

    def 'Should return minimum 2 authors'() {
        when: 'rest authors?username={query} url is hit'
        def response = mockMvc.perform(get('/authors?username=' + QUERY_DUMMY)).andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then: 'response is correct and minimum 2 authors returned'
        response.status == OK.value()
        response.contentType.contains('application/json')
        content.size >= 2

        cleanup:
        authorRepository.delete(testAuthor.getId())
        authorRepository.delete(testAuthor2.getId())
    }
}

