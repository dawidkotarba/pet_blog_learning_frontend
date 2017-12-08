package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.UserFacade;
import com.dawidkotarba.blog.model.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto findByUsername(@PathVariable final String username) {
        final Optional<UserDto> result = userFacade.findByUsername(username);
        return result.orElseThrow(() -> new NotFoundException("User [" + username + "]" + " not found."));
    }
}
