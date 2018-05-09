package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import com.dawidkotarba.blog.model.dto.OutDto;
import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class AuthorDto implements InDto, OutDto {

    @NotNull
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
}
