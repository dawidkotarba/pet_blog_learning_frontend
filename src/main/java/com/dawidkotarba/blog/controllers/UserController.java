package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.integration.dto.UserInDto;
import com.dawidkotarba.blog.integration.dto.UserOutDto;
import com.dawidkotarba.blog.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserOutDto> getAll() {
        return updateResourceResults(userService.getAll());
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserOutDto> getByName(@PathVariable String name) {
        return updateResourceResults(userService.getByName(name));
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody @Valid UserInDto userInDto) {
        userService.add(userInDto);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable String name) {
        userService.delete(name);
    }

    private List<UserOutDto> updateResourceResults(List<UserOutDto> dtos) {
        dtos.forEach(dto -> dto.add(linkTo(methodOn(UserController.class).getByName(dto.getUsername())).withSelfRel()));
        return dtos;
    }
}
