package com.dawidkotarba.blog.service;

import com.dawidkotarba.blog.dao.CountryDao;
import com.dawidkotarba.blog.integration.dto.CountryDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */

@Named
@Transactional
public class CountryService {

    private final CountryDao countryDao;

    @Inject
    public CountryService(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    public List<CountryDto> getAll() {
        return countryDao.getAll();
    }

    public List<CountryDto> getByName(String name) {
        return countryDao.getByName(name);
    }

    public void add(CountryDto countryDto) {
        countryDao.add(countryDto);
    }
}