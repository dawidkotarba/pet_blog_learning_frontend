package com.dawidkotarba.blog.auth.config;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.sql.DataSource;

import static com.dawidkotarba.blog.constants.BlogConstants.API_PREFIX;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("DEV")
class SecurityConfigDev {

    @Inject
    private DataSource dataSource;

    @Inject
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Disable CSRF and frame options for H2 console.
     * CSRF needs to be disabled for Mock MVC as well.
     *
     * @param http
     * @throws Exception
     */
    static void disableCsrfAndFrameOptions(final HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Configuration
    @Order(1)
    static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Inject
        private DaoAuthenticationProvider daoAuthenticationProvider;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(API_PREFIX + "**").permitAll()
                    .anyRequest().authenticated()
                    .and().httpBasic();

            disableCsrfAndFrameOptions(http);
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(daoAuthenticationProvider);
        }
    }

    @Configuration
    static class FormLoginWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Inject
        private DaoAuthenticationProvider daoAuthenticationProvider;

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/db/**").hasAuthority(UserAuthority.ADMINISTRATE.getAuthority())
                    .antMatchers("/swagger-ui.html").hasAnyAuthority(UserAuthority.ADMINISTRATE.getAuthority(), UserAuthority.WRITE.getAuthority())
                    .anyRequest().authenticated()
                    .and().formLogin();

            disableCsrfAndFrameOptions(http);
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(daoAuthenticationProvider);
        }
    }
}