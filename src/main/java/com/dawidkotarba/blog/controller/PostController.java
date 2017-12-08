package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.PostFacade;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts")
class PostController {

    private final PostFacade postFacade;

    @Inject
    PostController(final PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findAll() {
        return postFacade.findAll();
    }

    @RequestMapping(value = "/subject/{subject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findBySubject(@PathVariable final String subject) {
        final Optional<List<PostOutDto>> result = postFacade.findBySubject(subject);
        return result.orElseThrow(() -> new NotFoundException("Post with subject [" + subject + "]" + " not found."));
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody @Valid final PostInDto postDto) {
        postFacade.add(postDto);
    }

    @RequestMapping(value = "/search/{dayOfAMonth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findMontlyByDayOfAMonth(@RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dayOfAMonth) {
        return postFacade.findMontlyByDayOfAMonth(dayOfAMonth);
    }

    @RequestMapping(value = "/search/{fromDate}/{toDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findFromDayToDay(@RequestParam
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime fromDate,
                                      @RequestParam
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime toDate) {
        return postFacade.findFromDateToDate(fromDate, toDate);
    }
}
