package com.dawidkotarba.blog.service;

import com.dawidkotarba.blog.dao.UserDao;
import com.dawidkotarba.blog.integration.dto.UserInDto;
import com.dawidkotarba.blog.integration.dto.UserOutDto;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */

@Named
@Transactional
public class UserService {

    private final UserDao userDao;

    @Inject
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<UserOutDto> getAll() {
        return userDao.getAll();
    }

    public void add(UserInDto userInDto) {
        userDao.add(userInDto);
    }

    public void delete(String name) {
        userDao.delete(name);
    }

    public List<UserOutDto> getByName(String name) {
        return userDao.getByName(name);
    }
}
