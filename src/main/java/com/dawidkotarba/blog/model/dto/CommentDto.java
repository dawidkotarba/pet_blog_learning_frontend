package com.dawidkotarba.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto extends AbstractDto {

    @NotBlank
    private String author;
    @NotBlank
    private String subject;
    @NotBlank
    private String body;
    @NotNull
    private Date published;
}
