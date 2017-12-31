package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;

import java.util.Set;

public interface AuthorityRepository extends BaseRepository<AuthorityEntity> {

    AuthorityEntity findByAuthority(String authority);

    Set<AuthorityEntity> findAllByAuthorityStartingWithIgnoreCase(String username);
}
