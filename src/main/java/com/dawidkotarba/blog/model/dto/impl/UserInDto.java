package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import io.vavr.collection.Set;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserInDto implements InDto {

    @NotNull
    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotBlank
    private String password;
    private boolean enabled;
    @NotNull
    //    @NotEmpty // TODO: 14.05.18 enable when supported by vavr-jackson
    private Set<Long> authorities;
}
