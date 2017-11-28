package integration.com.dawidkotarba.dao;

import com.dawidkotarba.blog.dao.CountryDao;
import com.dawidkotarba.blog.integration.dto.CountryDto;
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
public class CountryDaoTest extends AbstractTestNgConfiguration {

    @Inject
    private CountryDao underTest;

    @Test
    public void getAllTest() {

        // given
        List<CountryDto> result = underTest.getAll();

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(greaterThan(1)));
    }

    @Test
    public void getByNameTest() {
        // given
        String name = "Poland";

        // when
        List<CountryDto> result = underTest.getByName(name);

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(1));
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
        CountryDto countryDto = new CountryDto();
        countryDto.setName("TestCountry");
        countryDto.setPopulation(100);

        // when
        underTest.add(countryDto);
        List<CountryDto> result = underTest.getByName("TestCountry");

        // then
        assertThat(result, is(notNullValue()));
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getName(), is(equalTo("TestCountry")));
        assertThat(result.get(0).getPopulation(), is(equalTo(100)));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addNullTest() {
        // given
        CountryDto countryDto = null;

        // when
        underTest.add(countryDto);
    }

    @Test
    public void getEmptyTest() {
        // given
        String name = "NotExistingCountry";

        // when
        List<CountryDto> result = underTest.getByName(name);

        // then
        assertThat(result, is(equalTo(Collections.EMPTY_LIST)));
    }
}
