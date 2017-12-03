package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.dto.UserDto;
import com.dawidkotarba.blog.facade.UserFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
class UserController {

    private final UserFacade userFacade;

    @Inject
    public UserController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getByName(@PathVariable final String name) {
        final Optional<UserDto> result = userFacade.findByName(name);
        if (result.isPresent()) {
            Collections.singletonList(result.get());
        }
        return Collections.emptyList();
    }
}
