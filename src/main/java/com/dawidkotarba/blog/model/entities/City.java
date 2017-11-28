package com.dawidkotarba.blog.model.entities;

import javax.persistence.*;

/**
 * Created by Dawid Kotarba on 08.02.2016.
 */
@Entity
@Table(name = "CITIES")
@SequenceGenerator(name = "PK", sequenceName = "CITIES_SEQ", allocationSize = 1)
public class City extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "POPULATION")
    private int population;

    @ManyToOne
    @JoinColumn(name = "COUNTRY")
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
