package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.UserFacade;
import com.dawidkotarba.blog.model.dto.impl.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
class UserController {

    private final UserFacade userFacade;

    @Inject
    UserController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    UserDto findByUsername(@PathVariable final String username) {
        final Optional<UserDto> result = userFacade.findByUsername(username);
        return result.orElseThrow(() -> new NotFoundException("User [" + username + "]" + " not found."));
    }
}
