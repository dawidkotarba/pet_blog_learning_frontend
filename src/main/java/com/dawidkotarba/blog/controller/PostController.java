package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.facade.PostFacade;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@CrossOrigin(origins = "http://localhost:4200")
class PostController {

    private final PostFacade postFacade;

    @Inject
    PostController(final PostFacade postFacade) {
        this.postFacade = postFacade;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Page<PostOutDto> findAll(final Pageable pageable) {
        return postFacade.findAll(pageable);
    }

    @GetMapping(value = "/subject/{subject}", produces = MediaType.APPLICATION_JSON_VALUE)
    PostOutDto findBySubject(@PathVariable final String subject) {
        final Optional<PostOutDto> result = postFacade.findBySubject(subject);
        return result.orElseThrow(() -> new NotFoundException("Post with subject [" + subject + "]" + " not found."));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    void add(@RequestBody @Valid final PostInDto postDto) {
        postFacade.add(postDto);
    }

    @GetMapping(value = "/search/{dayOfAMonth}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findMontlyByDayOfAMonth(@RequestParam
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate
                                                     dayOfAMonth) {
        return postFacade.findMontlyByDayOfAMonth(dayOfAMonth);
    }

    @GetMapping(value = "/search/{fromDate}/{toDate}", produces = MediaType.APPLICATION_JSON_VALUE)
    List<PostOutDto> findFromDayToDay(@RequestParam
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime fromDate,
                                      @RequestParam
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime toDate) {
        return postFacade.findFromDateToDate(fromDate, toDate);
    }
}
