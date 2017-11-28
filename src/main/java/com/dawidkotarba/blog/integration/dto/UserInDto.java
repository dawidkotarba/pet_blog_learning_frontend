package com.dawidkotarba.blog.integration.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */
@Data
public class UserInDto implements Serializable {

    @NotNull
    private String username;

    @NotNull
    private String password;

    private boolean enabled;

    @NotEmpty
    private String role;
}
