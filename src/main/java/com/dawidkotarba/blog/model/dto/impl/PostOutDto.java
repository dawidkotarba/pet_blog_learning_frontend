package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.OutDto;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

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
    private Set<CommentDto> commentDtos;
}
