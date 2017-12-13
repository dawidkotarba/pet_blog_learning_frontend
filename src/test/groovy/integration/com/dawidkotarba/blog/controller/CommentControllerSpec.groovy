package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.repository.CommentRepository
import groovy.json.JsonBuilder
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
class CommentControllerSpec extends Specification {

    @Inject
    CommentRepository commentRepository
    @Inject
    MockMvc mockMvc

    def 'Should add new comment'() {
        given:
        def TEST_VALUE = 'test'
        def TEST_COMMENT_ID = 1
        def TEST_POST_ID = 1
        def TEST_PUBLISHED_VALUE = '2017-12-11T08:06:56'
        def jsonBuilder = new JsonBuilder()
        jsonBuilder.call(
                {
                    author TEST_VALUE
                    subject TEST_VALUE
                    body TEST_VALUE
                    postId TEST_POST_ID
                    published TEST_PUBLISHED_VALUE
                }
        )

        when: 'rest add comment url is hit'
        def response = mockMvc.perform(post('/comments')
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonBuilder.toString())).andReturn().response

        then: 'response is correct and new comment is saved in db'
        response.status == OK.value()
        def comment = commentRepository.findOne(new Long(TEST_COMMENT_ID))
        comment != null
        comment.author == TEST_VALUE
        comment.subject == TEST_VALUE
        comment.body == TEST_VALUE
        comment.post.id == TEST_POST_ID
        comment.published.toString() == TEST_PUBLISHED_VALUE
    }
}
