package com.dawidkotarba.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Label {
    PROGRAMMING("Programming"),
    JAVA("Java"),
    LIFESTYLE("Lifestyle"),
    DB("DB"),
    NETWORK("Network");

    private String name;
}
