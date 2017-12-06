package com.dawidkotarba.blog.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class PostOutDto implements Serializable {

    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private Date published;
    @NotNull
    private AuthorDto author;
    private List<CommentDto> commentDtos;
}
