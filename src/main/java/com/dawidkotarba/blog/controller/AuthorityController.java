package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import com.dawidkotarba.blog.facade.AuthorityFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/authorities")
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
