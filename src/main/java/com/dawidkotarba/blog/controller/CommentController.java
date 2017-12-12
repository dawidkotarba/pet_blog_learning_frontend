package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.CommentFacade;
import com.dawidkotarba.blog.model.dto.impl.CommentInDto;
import com.dawidkotarba.blog.model.dto.impl.CommentOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping(value = "/comments")
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

    @GetMapping(value = "/postId/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
    Page<CommentOutDto> findByPostId(@PathVariable final Long postId, final Pageable pageable) {
        return commentFacade.findByPostId(postId, pageable);
    }
}
