package com.dawidkotarba.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto extends AbstractDto {

    @NotNull
    private String username;
    @NotNull
    private String firstname;
    @NotNull
    private String lastname;
    private boolean enabled;
    @NotNull
    private String role;
}
