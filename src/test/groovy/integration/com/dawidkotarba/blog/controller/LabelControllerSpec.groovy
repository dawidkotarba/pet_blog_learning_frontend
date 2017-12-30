package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.auth.enums.UserAuthority
import com.dawidkotarba.blog.repository.CacheableLabelRepository
import groovy.json.JsonSlurper
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class LabelControllerSpec extends Specification {

    @Inject
    MockMvc mockMvc

    @Inject
    CacheableLabelRepository repository

    def "should find all labels"() {
        when:
        def response = mockMvc.perform(get('/labels/all')
                .with((user("testuser").authorities([UserAuthority.WRITE]))))
                .andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then:
        response.status == OK.value()
        response.contentType.contains('application/json')
        repository.count() == content.size()
    }

    def "should find one label by name"() {
        given:
        def firstLabel = repository.findAll().get(0)

        when:
        def response = mockMvc.perform(get('/labels?name=' + firstLabel.label)
                .with((user("testuser").authorities([UserAuthority.WRITE]))))
                .andReturn().response
        def content = new JsonSlurper().parseText(response.contentAsString)

        then:
        response.status == OK.value()
        response.contentType.contains('application/json')
        content[0].label == firstLabel.label.getName()
    }
}