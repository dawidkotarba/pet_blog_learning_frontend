package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.LabelFacade;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/labels")
@CrossOrigin(origins = "http://localhost:4200")
public class LabelController {

    private final LabelFacade labelFacade;

    @Inject
    public LabelController(final LabelFacade labelFacade) {
        this.labelFacade = labelFacade;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<LabelDto> findAll() {
        return labelFacade.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<LabelDto> findByName(@RequestParam final String name) {
        return labelFacade.findByName(name);
    }
}
