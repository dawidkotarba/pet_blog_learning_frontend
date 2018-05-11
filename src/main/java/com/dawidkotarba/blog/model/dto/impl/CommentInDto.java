package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class CommentInDto implements InDto {

    @NotNull
    private Long id;
    @NotBlank
    private String author;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private Long postId;
    @NotNull
    private LocalDateTime published;
}
