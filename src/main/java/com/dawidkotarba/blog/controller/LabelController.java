package com.dawidkotarba.blog.controller;

import com.dawidkotarba.blog.facade.LabelFacade;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Set;

@RestController
@RequestMapping(value = "/labels")
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
}
