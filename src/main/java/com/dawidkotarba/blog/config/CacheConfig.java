package com.dawidkotarba.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * Created by Dawid Kotarba on 24.11.2015.
 */

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("classpath:ehcache.xml")
    private Resource ehCacheConfiguration;

    @Bean
    public CacheManager getEhCacheManager() {
        return new EhCacheCacheManager(ehCacheFactoryBean().getObject());

    }

    @Bean
    public EhCacheManagerFactoryBean ehCacheFactoryBean() {
        EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
        factoryBean.setConfigLocation(ehCacheConfiguration);
        factoryBean.setShared(true);
        return factoryBean;
    }
}
