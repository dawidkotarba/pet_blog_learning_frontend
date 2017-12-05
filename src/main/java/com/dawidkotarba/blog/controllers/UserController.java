package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.UserFacade;
import com.dawidkotarba.blog.model.dto.UserDto;
import com.dawidkotarba.blog.service.i18n.LocalizationService;
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
    private final LocalizationService localizationService;

    @Inject
    public UserController(final UserFacade userFacade, final LocalizationService localizationService) {
        this.userFacade = userFacade;
        this.localizationService = localizationService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAll() {
        return userFacade.findAll();
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getByName(@PathVariable final String name) {
        final Optional<UserDto> result = userFacade.findByName(name);

        return result.orElseThrow(() -> new NotFoundException(localizationService.getMessage("user.not.found", new String[]{name})));
    }
}
