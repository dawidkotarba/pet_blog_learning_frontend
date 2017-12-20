package com.dawidkotarba.blog.auth.config;

import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.constants.BlogConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Profile("PROD")
class SecurityConfigProd extends WebSecurityConfigurerAdapter {

    @Inject
    private DataSource dataSource;

    @Inject
    private SecurityConfigService securityConfigService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/db/**").hasAuthority(UserAuthority.ADMINISTRATE.getAuthority())
                .antMatchers("/swagger-ui.html").hasAnyAuthority(UserAuthority.ADMINISTRATE.getAuthority(), UserAuthority.WRITE.getAuthority())
                .antMatchers("/posts/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/comments/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic().realmName(BlogConstants.BASIC_AUTH_REALM);
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    public void configure(final WebSecurity web) {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
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