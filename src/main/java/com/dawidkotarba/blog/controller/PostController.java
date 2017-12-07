package com.dawidkotarba.blog.controllers;

import com.dawidkotarba.blog.facade.PostFacade;
import com.dawidkotarba.blog.model.dto.PostInDto;
import com.dawidkotarba.blog.model.dto.PostOutDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public List<PostOutDto> findAll() {
        return postFacade.findAll();
    }

    @RequestMapping(value = "/subject/{subject}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostOutDto> findBySubject(@PathVariable final String subject) {
        return postFacade.findBySubject(subject);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody @Valid final PostInDto postDto) {
        postFacade.add(postDto);
    }

    @RequestMapping(value = "/search/{dayOfAMonth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostOutDto> findMontlyByDayOfAMonth(@RequestParam("dayOfAMonth")
                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) final LocalDate dayOfAMonth) {
        return postFacade.findMontlyByDayOfAMonth(dayOfAMonth);
    }

    @RequestMapping(value = "/search/{fromDate}/{toDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PostOutDto> findFromDayToDay(@RequestParam("fromDate")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime fromDate,
                                          @RequestParam("toDate")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime toDate) {
        return postFacade.findFromDateToDate(fromDate, toDate);
    }
}
