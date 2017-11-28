package com.dawidkotarba.blog.model.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */

@Entity
@Table(name = "COUNTRIES")
@SequenceGenerator(name = "PK", sequenceName = "COUNTRIES_SEQ", allocationSize = 1)
public class Country extends AbstractEntity {

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CAPITAL")
    private City capital = new City();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Set<City> cities = new LinkedHashSet<>();

    @Column(name = "AREA")
    private int area;

    @Column(name = "POPULATION")
    private int population;

    @Column(name = "CURRENCY")
    private String currency;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "NEIGHBOURS",
        joinColumns = {@JoinColumn(name = "COUNTRY")},
        inverseJoinColumns = {@JoinColumn(name = "NEIGHBOUR")})
    private Set<Country> neighbours = new LinkedHashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set<Country> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Country> neighbours) {
        this.neighbours = neighbours;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }
}
