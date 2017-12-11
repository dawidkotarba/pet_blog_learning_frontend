package integration.com.dawidkotarba.blog.controller

import com.dawidkotarba.blog.repository.CommentRepository
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.inject.Inject

import static org.springframework.http.HttpStatus.OK
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@SpringBootTest(classes = com.dawidkotarba.blog.BlogApp.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@Transactional
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
        def requestBody = '{\n' +
                '  "id": 0,\n' +
                '  "author": "' + TEST_VALUE + '",\n' +
                '  "subject": "' + TEST_VALUE + '",\n' +
                '  "body": "' + TEST_VALUE + '",\n' +
                '  "postId": ' + TEST_POST_ID + ',\n' +
                '  "published": "' + TEST_PUBLISHED_VALUE + '"\n' +
                '}'

        when: 'rest add comment url is hit'
        def response = mockMvc.perform(post('/comments')
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)).andReturn().response

        then: 'response is correct and new comment is saved in db'
        response.status == OK.value()
        def comment = commentRepository.findOne(new Long(TEST_COMMENT_ID))
        comment != null
        comment.author == TEST_VALUE
        comment.subject == TEST_VALUE
        comment.body == TEST_VALUE
        comment.post.id == TEST_POST_ID
        comment.published.toLocalDateTime().toString() == TEST_PUBLISHED_VALUE
    }
}
