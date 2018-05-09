package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.OutDto;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class PostOutDto implements OutDto {

    @NotNull
    private Long id;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private LocalDateTime published;
    @NotNull
    @NotEmpty
    private Set<AuthorDto> authors;
    private Set<LabelDto> labels;
    private Set<CommentOutDto> commentDtos;
}
