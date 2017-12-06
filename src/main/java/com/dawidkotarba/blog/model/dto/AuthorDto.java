package com.dawidkotarba.blog.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@Builder
public class AuthorDto implements Serializable {

    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
}
