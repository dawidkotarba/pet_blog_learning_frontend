package com.dawidkotarba.blog.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * Created by Dawid Kotarba on 13.11.2015.
 */

@Configuration
@ConfigurationProperties(prefix = "default")
public class LocalizationConfig extends WebMvcConfigurerAdapter {

    private String locale;
    private Locale defaultLocale;

    @PostConstruct
    public void init() {
        defaultLocale = new Locale.Builder().setLanguageTag(locale).build();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(defaultLocale);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        String[] resources = {"classpath:i18n/messages", "classpath:exceptions/exceptions"};
        messageSource.setBasenames(resources);
        return messageSource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Locale getDefaultLocale() {
        return defaultLocale;
    }
}
