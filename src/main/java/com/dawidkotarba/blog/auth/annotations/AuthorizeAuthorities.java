package com.dawidkotarba.blog.auth.annotations;

import com.dawidkotarba.blog.auth.enums.UserAuthority;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.METHOD)
public @interface AuthorizeAuthorities {
    UserAuthority[] authorities();
}