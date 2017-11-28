package com.dawidkotarba.blog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */
@Configuration
@EnableWebMvcSecurity
@Profile("DEV") //-Dspring.profiles.active=DEV
public class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/*").permitAll();

        // h2 console csrf disable
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
