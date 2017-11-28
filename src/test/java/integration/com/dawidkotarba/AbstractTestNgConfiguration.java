package integration.com.dawidkotarba;

import com.dawidkotarba.blog.BlogApp;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Dawid Kotarba on 17.11.2015.
 */

@SpringApplicationConfiguration(classes = BlogApp.class)
@WebAppConfiguration
@IntegrationTest
@TestExecutionListeners(inheritListeners = false, listeners = {
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class})
public abstract class AbstractTestNgConfiguration extends AbstractTransactionalTestNGSpringContextTests {

    protected AbstractTestNgConfiguration() {
        // intentionally left blank
    }
}
