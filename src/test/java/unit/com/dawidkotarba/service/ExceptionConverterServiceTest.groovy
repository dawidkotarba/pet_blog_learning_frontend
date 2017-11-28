package unit.com.dawidkotarba.service;

import com.dawidkotarba.blog.enums.ExceptionType;
import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.integration.exceptions.ExceptionResponse;
import com.dawidkotarba.blog.service.ExceptionConverterService;
import com.dawidkotarba.blog.service.i18n.LocalizationService;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.MethodParameter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dawid Kotarba on 19.11.2015.
 */
public class ExceptionConverterServiceTest {

    private ExceptionConverterService underTest;

    @Mock
    private LocalizationService localizationService;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);
        underTest = new ExceptionConverterService(localizationService);
    }

    @Test
    public void convertApplicationRuntimeExceptionTest() {
        // given
        String devMsg = "Test not found exception";
        NotFoundException notFoundException = new NotFoundException(devMsg);

        // when
        ExceptionResponse result = underTest.convert(notFoundException);

        // then
        assertThat(result.getDevMessage(), is(equalTo(devMsg)));
        assertThat(result.getUuid(), is(notNullValue()));
        assertThat(result.getExceptionType(), is(equalTo(ExceptionType.NOT_FOUND)));
    }

    @Test
    public void convertExceptionTest() {
        // given
        String devMsg = "Test not found exception";
        Exception exception = new Exception(devMsg);

        // when
        ExceptionResponse result = underTest.convert(exception);

        // then
        assertThat(result.getDevMessage(), is(equalTo(devMsg)));
        assertThat(result.getUuid(), is(notNullValue()));
        assertThat(result.getExceptionType(), is(equalTo(ExceptionType.INTERNAL_ERROR)));
    }

    @Test
    public void convertMethodArgumentNotValidExceptionTest() {
        // given
        BindingResult errors = Mockito.mock(BindingResult.class);
        Mockito.when(errors.hasFieldErrors()).thenReturn(true);
        String testFieldName = "fieldName";
        String testDefMsg = "defMsg";

        Mockito.when(errors.getFieldErrors()).thenReturn(Arrays.asList(new FieldError("objectName", testFieldName, testDefMsg)));
        MethodParameter parameter = new MethodParameter(this.getClass().getMethods()[0], 0);
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(parameter, errors);

        // when
        ExceptionResponse result = underTest.convert(exception, exception.getBindingResult());

        // then
        assertThat(result.getUuid(), is(notNullValue()));
        assertThat(result.getExceptionType(), is(equalTo(ExceptionType.INTERNAL_ERROR)));
        assertThat(result.getValidationErrors(), is(notNullValue()));
        assertThat(result.getValidationErrors().get(0).getFieldName(), is(equalTo(testFieldName)));
        assertThat(result.getValidationErrors().get(0).getMessage(), is(equalTo(testDefMsg)));
    }
}
