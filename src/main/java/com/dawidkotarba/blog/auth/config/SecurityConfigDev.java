package com.dawidkotarba.blog.auth.config;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@Profile("DEV")
class SecurityConfigDev extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    @Inject
    private SecurityConfigService securityConfigService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/db").hasAuthority(UserAuthority.ADMINISTRATE.getAuthority())
                .antMatchers("/swagger-ui.html").hasAnyAuthority(UserAuthority.ADMINISTRATE.getAuthority(), UserAuthority.WRITE.getAuthority())
                .anyRequest().permitAll()
                .and().formLogin();

        // h2 console csrf disable
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Inject
    void configureAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).
                usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?").authoritiesByUsernameQuery(
                "SELECT u.username, auth.authority FROM users AS u JOIN users_authorities AS ua ON u.id = ua.user_id "
                        + "JOIN authorities AS auth ON ua.authority_id = auth.id WHERE u.username=?");
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        final DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(securityConfigService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}