package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.integration.dto.CountryDto;
import com.dawidkotarba.blog.service.CountryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    private final CountryService countryService;

    @Inject
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryDto> getAll() {
        return updateResults(countryService.getAll());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CountryDto> getByName(@PathVariable String name) {
        return updateResults(countryService.getByName(name));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody @Valid CountryDto countryDto) {
        countryService.add(countryDto);
    }

    private List<CountryDto> updateResults(List<CountryDto> dtos) {
        dtos.forEach(dto -> dto.add(linkTo(methodOn(CountryController.class).getByName(dto.getName())).withSelfRel()));
        return dtos;
    }
}
