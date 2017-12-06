package com.dawidkotarba.blog.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
public class CommentDto implements Serializable {

    @NotBlank
    private String author;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private Date published;
}
