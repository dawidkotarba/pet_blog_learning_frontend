package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import com.dawidkotarba.blog.facade.AuthorityFacade;
import io.vavr.collection.Set;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import static com.dawidkotarba.blog.constants.BlogConstants.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "authorities")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorityController {

    private final AuthorityFacade authorityFacade;

    @Inject
    public AuthorityController(final AuthorityFacade authorityFacade) {
        this.authorityFacade = authorityFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<AuthorityEntity> findAllStartsWith(@RequestParam final String authority) {
        return authorityFacade.findAllByUsernameStartingWithIgnoreCase(authority);
    }
}
