package com.dawidkotarba.blog.service.populationDensityCalculator;

/**
 * Created by Dawid Kotarba on 19.12.2015.
 */
public class PopulationDensityCalculationInputData {

    private int population;
    private int area;

    public PopulationDensityCalculationInputData(int population, int area) {
        this.population = population;
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public int getArea() {
        return area;
    }
}
