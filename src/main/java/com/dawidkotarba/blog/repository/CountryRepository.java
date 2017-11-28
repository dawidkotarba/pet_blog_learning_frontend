package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dawid Kotarba on 17.01.2016.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
    List<Country> findByName(String name);
}
