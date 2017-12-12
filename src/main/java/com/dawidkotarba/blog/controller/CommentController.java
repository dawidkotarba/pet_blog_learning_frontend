package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.CommentFacade;
import com.dawidkotarba.blog.model.dto.impl.CommentInDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/comments")
@CrossOrigin(origins = "http://localhost:4200")
class CommentController {

    private final CommentFacade commentFacade;

    @Inject
    CommentController(final CommentFacade commentFacade) {
        this.commentFacade = commentFacade;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody final CommentInDto commentInDto) {
        commentFacade.add(commentInDto);
    }
}
