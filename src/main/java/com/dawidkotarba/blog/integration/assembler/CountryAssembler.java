package com.dawidkotarba.blog.integration.assembler;

import com.dawidkotarba.blog.integration.dto.CityDto;
import com.dawidkotarba.blog.integration.dto.CountryDto;
import com.dawidkotarba.blog.model.entities.City;
import com.dawidkotarba.blog.model.entities.Country;
import org.springframework.beans.BeanUtils;

import javax.inject.Named;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Dawid Kotarba on 01.12.2015.
 */
@Named
public class CountryAssembler {

    private Set<String> assembleNeighbourNames(Country country) {
        return country.getNeighbours().stream().map(n -> n.getName()).collect(Collectors.toSet());
    }

    private Set<CityDto> assembleCities(Country country) {
        return country.getCities().stream().map(convertCityToDto()).collect(Collectors.toSet());
    }

    private Function<City, CityDto> convertCityToDto() {
        return city -> {
            CityDto cityDto = new CityDto();
            cityDto.setName(city.getName());
            cityDto.setPopulation(city.getPopulation());
            return cityDto;
        };
    }

    public Function<Country, CountryDto> convertCountryToDto() {
        return country -> {
            CountryDto countryDto = new CountryDto();
            BeanUtils.copyProperties(country, countryDto);
            countryDto.setCapital(convertCityToDto().apply(country.getCapital()));
            countryDto.setCities(assembleCities(country));
            countryDto.setNeighbourCountriesNames(assembleNeighbourNames(country));

            return countryDto;
        };
    }

    private Function<CityDto, City> transformCityDtoToEntity() {
        return cityDto -> {
            City city = new City();
            city.setName(cityDto.getName());
            city.setPopulation(cityDto.getPopulation());
            return city;
        };
    }

    public Function<CountryDto, Country> transformCountryDtoToEntity() {
        return countryDto -> {
            Country country = new Country();
            country.setName(countryDto.getName());
            country.setCapital(assembleCapital(countryDto, country));
            country.setArea(countryDto.getArea());
            country.setCurrency(countryDto.getCurrency());
            country.setPopulation(countryDto.getPopulation());

            Set<City> cities = countryDto.getCities().stream().map(transformCityDtoToEntity()).collect(Collectors.toSet());
            country.setCities(cities);

            return country;
        };
    }

    private City assembleCapital(CountryDto countryDto, Country country) {
        City capital = new City();
        capital.setName(countryDto.getCapital().getName());
        capital.setPopulation(countryDto.getCapital().getPopulation());
        capital.setCountry(country);
        return capital;
    }
}
