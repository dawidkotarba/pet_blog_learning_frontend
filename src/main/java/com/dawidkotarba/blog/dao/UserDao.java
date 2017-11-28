package com.dawidkotarba.blog.dao;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.integration.assembler.UserAssembler;
import com.dawidkotarba.blog.integration.dto.UserInDto;
import com.dawidkotarba.blog.integration.dto.UserOutDto;
import com.dawidkotarba.blog.model.entities.User;
import com.dawidkotarba.blog.repository.UserRepository;
import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
 * Created by Dawid Kotarba on 15.11.2015.
 */

@Named
@Transactional(propagation = Propagation.MANDATORY)
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private UserRepository userRepository;

    @Inject
    private UserAssembler userAssembler;

    public List<UserOutDto> getAll() {
        return userRepository.findAll().stream().map(userAssembler.convert()).collect(Collectors.toList());
    }

    public List<UserOutDto> getByName(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be blank");
        return userRepository.findByUsername(name).stream().map(userAssembler.convert()).collect(Collectors.toList());
    }

    public void add(UserInDto userInDto) {
        Preconditions.checkNotNull(userInDto, "userInDto cannot be null");

        User user = new User();
        BeanUtils.copyProperties(userInDto, user);
        entityManager.persist(user);
    }

    public void delete(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Name cannot be blank");
        List<User> result = userRepository.findByUsername(name);

        if (result.isEmpty()) {
            throw new NotFoundException("Cannot find a country with name " + name);
        }

        User country = result.get(0);
        entityManager.remove(country);
    }

}
