package com.dawidkotarba.blog.integration.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */
@Data
public class CountryDto extends ResourceSupport implements Serializable {

    @NotNull
    private String name;

    @Valid
    private CityDto capital = new CityDto();

    private Set<CityDto> cities = new LinkedHashSet<>();

    private int area;

    private int population;

    @Size(max = 3)
    private String currency;

    private Set<String> neighbourCountriesNames = new LinkedHashSet<>();
}
