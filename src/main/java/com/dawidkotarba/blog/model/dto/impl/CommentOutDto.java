package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.OutDto;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentOutDto implements OutDto {

    @NotNull
    private Long id;
    @NotBlank
    private String author;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private LocalDateTime published;
}
