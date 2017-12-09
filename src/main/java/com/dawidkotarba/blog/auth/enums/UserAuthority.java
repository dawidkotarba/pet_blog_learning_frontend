package com.dawidkotarba.blog.auth.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserAuthority implements GrantedAuthority {
    ADMINISTRATE {
        @Override
        public String getAuthority() {
            return name().toLowerCase();
        }
    },
    WRITE {
        @Override
        public String getAuthority() {
            return name().toLowerCase();
        }
    },
    READ {
        @Override
        public String getAuthority() {
            return name().toLowerCase();
        }
    }
}