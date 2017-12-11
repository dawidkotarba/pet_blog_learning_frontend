package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class PostInDto implements InDto {

    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime published;
    @NotNull
    @NotEmpty
    private Set<Long> authors;
}
