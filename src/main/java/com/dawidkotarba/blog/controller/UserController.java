package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.UserFacade;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.dto.impl.UserOutDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    List<UserOutDto> findAll() {
        return userFacade.findAll();
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserOutDto findByUsername(@PathVariable final String username) {
        final Optional<UserOutDto> result = userFacade.findByUsername(username);
        return result.orElseThrow(() -> new NotFoundException("User [" + username + "]" + " not found."));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody final UserInDto userInDto) {
        userFacade.add(userInDto);
    }
}
