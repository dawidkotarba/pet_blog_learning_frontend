package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.facade.PostFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
class PostController {

    private final PostFacade postFacade;

    @Inject
    public PostController(final PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> findAll() {
        postFacade.findAll();
        return Collections.emptyList();
    }
}
