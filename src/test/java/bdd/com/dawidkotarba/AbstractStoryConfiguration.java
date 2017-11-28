package bdd.com.dawidkotarba;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CasePreservingResolver;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

/**
 * Created by Dawid Kotarba on 19.12.2015.
 */
public abstract class AbstractStoryConfiguration extends JUnitStory {

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration().useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryPathResolver(new CasePreservingResolver())
            .usePendingStepStrategy(new FailingUponPendingStep())
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withDefaultFormats().withFormats(Format.CONSOLE, Format.TXT));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepInstances());
    }

    public abstract Object[] stepInstances();
}
