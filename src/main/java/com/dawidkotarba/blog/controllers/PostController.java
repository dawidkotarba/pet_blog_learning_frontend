package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.facade.PostFacade;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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
        return postFacade.findAll();
    }

    @RequestMapping(value = "/{subject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostDto> findBySubject(@PathVariable String subject) {
        return postFacade.findBySubject(subject);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody PostDto postDto) {
        postFacade.add(postDto);
    }
}
