package bdd.com.dawidkotarba.steps;

import com.dawidkotarba.blog.service.populationDensityCalculator.PopulationDensityCalculationInputData;
import com.dawidkotarba.blog.service.populationDensityCalculator.PopulationDensityCalculatorService;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Dawid Kotarba on 19.12.2015.
 */
public class PopulationDensityCalculatorSteps {

    private PopulationDensityCalculatorService underTest = new PopulationDensityCalculatorService();

    private PopulationDensityCalculationInputData inputData;

    @Given("a <population> and <area>")
    public void given(@Named("population") int population, @Named("area") int area) {
        inputData = new PopulationDensityCalculationInputData(population, area);
    }

    @Then("the population density should be <result>")
    public void then(@Named("result") int result) {
        int calculationResult = underTest.calculate(inputData);

        assertThat(result, is(equalTo(calculationResult)));
    }
}
