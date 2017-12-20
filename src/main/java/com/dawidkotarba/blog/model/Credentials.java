package com.dawidkotarba.blog.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
public class Credentials {
    @NotBlank
    String username;
    @NotBlank
    String password;
}
