package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.AuthorConverter;
import com.dawidkotarba.blog.model.dto.impl.AuthorDto;
import com.dawidkotarba.blog.repository.CacheableAuthorRepository;
import io.vavr.collection.Set;

import javax.inject.Inject;
import javax.inject.Named;

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
        return cacheableAuthorRepository
                .findAllByUsernameStartingWithIgnoreCase(username)
                .map(authorConverter::convert).toSet();
    }
}
