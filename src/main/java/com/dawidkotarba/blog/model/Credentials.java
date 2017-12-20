package com.dawidkotarba.blog.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Credentials {
    String username;
    String password;
}
