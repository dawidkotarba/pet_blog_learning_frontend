package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.UserFacade;
import com.dawidkotarba.blog.model.dto.impl.UserInDto;
import com.dawidkotarba.blog.model.dto.impl.UserOutDto;
import io.vavr.collection.Set;
import io.vavr.control.Option;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static com.dawidkotarba.blog.constants.BlogConstants.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "users")
@CrossOrigin(origins = "http://localhost:4200")
class UserController {

    private final UserFacade userFacade;

    @Inject
    UserController(final UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Set<UserOutDto> findAll() {
        return userFacade.findAll();
    }

    @GetMapping(value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserOutDto findByUsername(@PathVariable final String username) {
        final Option<UserOutDto> result = userFacade.findByUsername(username);
        return result.getOrElseThrow(() -> new NotFoundException("User [" + username + "]" + " not found."));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody final UserInDto userInDto) {
        userFacade.add(userInDto);
    }
}
