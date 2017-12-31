package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.AuthorConverter;
import com.dawidkotarba.blog.model.dto.impl.AuthorDto;
import com.dawidkotarba.blog.repository.CacheableAuthorRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class AuthorFacade {

    private final AuthorConverter authorConverter;
    private final CacheableAuthorRepository cacheableAuthorRepository;

    @Inject
    public AuthorFacade(final CacheableAuthorRepository cacheableAuthorRepository, final AuthorConverter
            authorConverter) {
        this.cacheableAuthorRepository = cacheableAuthorRepository;
        this.authorConverter = authorConverter;
    }

    public Set<AuthorDto> findAllByUsernameStartingWithIgnoreCase(final String username) {
        final Set<AuthorDto> result = cacheableAuthorRepository.findAllByUsernameStartingWithIgnoreCase(username).stream()
                .map(authorConverter::convert).collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);
    }
}
