package unit.com.dawidkotarba.service;

import com.dawidkotarba.blog.dao.UserDao;
import com.dawidkotarba.blog.integration.dto.UserInDto;
import com.dawidkotarba.blog.integration.dto.UserOutDto;
import com.dawidkotarba.blog.service.UserService;
import org.mockito.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by Dawid Kotarba on 19.11.2015.
 */
class UserServiceTest {

    @InjectMocks
    private UserService underTest;

    @Mock
    private UserDao userDao;

    @Captor
    private ArgumentCaptor<String> nameCaptor;

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() {

        // given
        doReturn(new ArrayList<UserOutDto>()).when(userDao).getAll();

        // when
        List<UserOutDto> result = underTest.getAll();

        // then
        assertThat(result, is(notNullValue()));
        verify(userDao).getAll();
        verifyNoMoreInteractions(userDao);
    }

    @Test
    public void getByNameTest() {
        // given
        String testName = "test";
        doReturn(new ArrayList<UserOutDto>()).when(userDao).getByName(testName);

        // when
        List<UserOutDto> result = underTest.getByName(testName);

        // then
        assertThat(result, is(notNullValue()));
        verify(userDao).getByName(anyString());

        verify(userDao).getByName(nameCaptor.capture());
        assertThat(nameCaptor.getValue(), is(equalTo(testName)));

        verifyNoMoreInteractions(userDao);
    }

    @Test
    public void addTest() {
        // when
        underTest.add(new UserInDto());

        // then
        verify(userDao).add(any());
        verifyNoMoreInteractions(userDao);
    }
}
