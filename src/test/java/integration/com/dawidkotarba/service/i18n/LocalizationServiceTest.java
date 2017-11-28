package integration.com.dawidkotarba.service.i18n;

/**
 * Created by Dawid Kotarba on 14.11.2015.
 */

import com.dawidkotarba.blog.service.i18n.LocalizationService;
import integration.com.dawidkotarba.AbstractTestNgConfiguration;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.testng.Assert.assertEquals;


public class LocalizationServiceTest extends AbstractTestNgConfiguration {

    @Inject
    private LocalizationService underTest;

    @Test
    public void getMessageTest() {
        String result = underTest.getMessage("app.name");
        assertEquals(result, "Playground");
    }
}
