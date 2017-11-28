package com.dawidkotarba.blog.dao;

import com.dawidkotarba.blog.integration.assembler.CountryAssembler;
import com.dawidkotarba.blog.integration.dto.CountryDto;
import com.dawidkotarba.blog.model.entities.Country;
import com.dawidkotarba.blog.repository.CountryRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dawid Kotarba on 12.11.2015.
 */

@Named
@Transactional(propagation = Propagation.MANDATORY)
public class CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private CountryRepository countryRepository;

    @Inject
    private CountryAssembler countryAssembler;

    public List<CountryDto> getAll() {
        return countryRepository.findAll().stream().map(countryAssembler.convertCountryToDto()).collect(Collectors.toList());
    }

    @Cacheable("countriesCache")
    public List<CountryDto> getByName(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be blank");
        return countryRepository.findByName(name).stream().map(countryAssembler.convertCountryToDto()).collect(Collectors.toList());
    }

    public void add(CountryDto countryDto) {
        Preconditions.checkNotNull(countryDto, "countryDto cannot be null");

        Country country = countryAssembler.transformCountryDtoToEntity().apply(countryDto);
        entityManager.persist(country);
    }

    /*
    // ================ example of JPA Criteria API ================

        public List<CountryDto> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> countryCriteriaQuery = criteriaBuilder.createQuery(Country.class);

        Root<Country> from = countryCriteriaQuery.from(Country.class);
        CriteriaQuery<Country> select = countryCriteriaQuery.select(from);

        TypedQuery<Country> typedQuery = entityManager.createQuery(select);
        List<Country> result = typedQuery.getResultList();

        return countryAssembler.convertToDto(result);
    }

    @Cacheable("countriesCache")
    public List<CountryDto> getByName(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be blank");

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Country> countryCriteriaQuery = criteriaBuilder.createQuery(Country.class);

        Root<Country> from = countryCriteriaQuery.from(Country.class);
        CriteriaQuery<Country> select = countryCriteriaQuery.select(from);

        Predicate namePredicate = criteriaBuilder.equal(from.get("name"), name);

        select.where(namePredicate);

        TypedQuery<Country> typedQuery = entityManager.createQuery(select);
        List<Country> result = typedQuery.getResultList();

        return countryAssembler.convertToDto(result);
    }

    // ================ example of Querydsl ================

      public List<CountryDto> getAll() {
        List<Country> result = new JPAQuery(entityManager).from(country).fetchAll().list(country);
        return countryAssembler.convertToDto(result);
    }

    @Cacheable("countriesCache")
    public List<CountryDto> getByName(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be blank");

        List<Country> result = new JPAQuery(entityManager).from(country).where(country.name.containsIgnoreCase(name)).list(country);
        return countryAssembler.convertToDto(result);
    }

     */
}
