package com.dawidkotarba.blog.integration.dto;

import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */

@Data
public class UserOutDto extends ResourceSupport implements Serializable {

    private Long userId;

    private String username;

    private boolean enabled;

    private String role;
}
