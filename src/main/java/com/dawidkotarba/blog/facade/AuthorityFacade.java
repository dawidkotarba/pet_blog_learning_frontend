package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import com.dawidkotarba.blog.repository.AuthorityRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Set;

@Named
public class AuthorityFacade {
    private final AuthorityRepository authorityRepository;

    @Inject
    public AuthorityFacade(final AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Set<AuthorityEntity> findAllByUsernameStartingWithIgnoreCase(final String authority) {
        final Set<AuthorityEntity> result = authorityRepository.findAllByAuthorityStartingWithIgnoreCase(authority);
        return Collections.unmodifiableSet(result);
    }
}
