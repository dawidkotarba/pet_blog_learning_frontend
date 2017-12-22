package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import com.dawidkotarba.blog.repository.AuthorityRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class AuthorityFacade {
    private final AuthorityRepository authorityRepository;

    @Inject
    public AuthorityFacade(final AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public List<AuthorityEntity> findAllByUsernameStartingWithIgnoreCase(final String authority) {
        return authorityRepository.findAllByAuthorityStartingWithIgnoreCase(authority);
    }
}
