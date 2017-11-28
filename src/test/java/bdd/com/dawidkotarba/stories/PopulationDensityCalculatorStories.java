package bdd.com.dawidkotarba.stories;

import bdd.com.dawidkotarba.AbstractStoryConfiguration;
import bdd.com.dawidkotarba.steps.PopulationDensityCalculatorSteps;
import org.testng.annotations.Test;

/**
 * Created by Dawid Kotarba on 19.12.2015.
 */
public class PopulationDensityCalculatorStories extends AbstractStoryConfiguration {
    @Override
    public Object[] stepInstances() {
        return new Object[] {new PopulationDensityCalculatorSteps()};
    }

    @Test
    @Override
    public void run() throws Throwable {
        super.run();
    }
}