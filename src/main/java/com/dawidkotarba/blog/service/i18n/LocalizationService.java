package com.dawidkotarba.blog.service.i18n;

import com.dawidkotarba.blog.config.LocalizationConfig;
import org.springframework.context.MessageSource;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Dawid Kotarba on 14.11.2015.
 */

@Named
public class LocalizationService {

    private final MessageSource messageSource;
    private final LocalizationConfig localizationConfig;

    @Inject
    public LocalizationService(MessageSource messageSource, LocalizationConfig localizationConfig) {
        this.messageSource = messageSource;
        this.localizationConfig = localizationConfig;
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, localizationConfig.getDefaultLocale());
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, localizationConfig.getDefaultLocale());
    }
}
