package com.dawidkotarba.blog.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.inject.Inject;
import javax.sql.DataSource;

/**
 * Created by Dawid Kotarba on 15.11.2015.
 */
@Configuration
@EnableWebMvcSecurity
@Profile("PROD") //-Dspring.profiles.active=PROD
public class SecurityConfigProd extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/db").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests()
            .antMatchers("/users/*").access("hasRole('ROLE_ADMIN')");

        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .and()
            .httpBasic();

        // h2 console csrf disable
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Inject
    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
            usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?").authoritiesByUsernameQuery(
            "SELECT username, role FROM users WHERE username=?");
    }
}
