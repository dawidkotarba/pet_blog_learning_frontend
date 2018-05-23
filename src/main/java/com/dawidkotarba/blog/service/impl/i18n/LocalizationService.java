package com.dawidkotarba.blog.service.impl.i18n;

import com.dawidkotarba.blog.config.LocalizationConfig;
import org.springframework.context.MessageSource;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class LocalizationService {

    private final MessageSource messageSource;
    private final LocalizationConfig localizationConfig;

    @Inject
    public LocalizationService(final MessageSource messageSource, final LocalizationConfig localizationConfig) {
        this.messageSource = messageSource;
        this.localizationConfig = localizationConfig;
    }

    public String getMessage(final String code) {
        return messageSource.getMessage(code, null, localizationConfig.getDefaultLocale());
    }

    public String getMessage(final String code, final Object[] args) {
        return messageSource.getMessage(code, args, localizationConfig.getDefaultLocale());
    }
}
