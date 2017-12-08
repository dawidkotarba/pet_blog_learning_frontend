package com.dawidkotarba.blog.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class PostInDto implements Serializable {

    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private LocalDateTime published;
    @NotNull
    @NotEmpty
    private Set<AuthorDto> authors;
}
