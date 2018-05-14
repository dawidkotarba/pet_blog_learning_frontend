package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import io.vavr.collection.Set;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PostInDto implements InDto {

    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private LocalDateTime published;
    @NotNull
//    @NotEmpty // TODO: 14.05.18 enable when supported by vavr-jackson
    private Set<Long> authors;
    private Set<Long> labels;
}
