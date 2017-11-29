package integration.com.dawidkotarba.dao;

import com.dawidkotarba.blog.dao.UserDao;
import com.dawidkotarba.blog.integration.dto.UserInDto;
import com.dawidkotarba.blog.integration.dto.UserOutDto;
import integration.com.dawidkotarba.AbstractTestNgConfiguration;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Dawid Kotarba on 18.11.2015.
 */
public class UserEntityDaoTest extends AbstractTestNgConfiguration {

    @Inject
    private UserDao underTest;

    @Test
    public void getAllTest() {

        // given
        List<UserOutDto> result = underTest.getAll();

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(greaterThan(1)));
    }

    @Test
    public void getByNameTest() {
        // given
        String name = "admin";

        // when
        List<UserOutDto> result = underTest.getByName(name);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getUsername(), is(equalTo(name)));
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void getByEmptyNameTest() {
        // given
        String name = "";

        // when
        underTest.getByName(name);
    }

    @Test
    public void addTest() {
        // given
        UserInDto userInDto = new UserInDto();
        userInDto.setUsername("TestUser");
        userInDto.setPassword("TestPassword");
        userInDto.setRole("TestRole");

        // when
        underTest.add(userInDto);
        List<UserOutDto> result = underTest.getByName("TestUser");

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getUsername(), is(equalTo("TestUser")));
        assertThat(result.get(0).getRole(), is(equalTo("TestRole")));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullTest() {
        // given
        UserInDto userInDto = null;

        // when
        underTest.add(userInDto);
    }

    @Test
    public void getEmptyTest() {
        // given
        String name = "NotExistingUser";

        // when
        List<UserOutDto> result = underTest.getByName(name);

        // then
        assertThat(result, is(equalTo(Collections.EMPTY_LIST)));
    }
}
