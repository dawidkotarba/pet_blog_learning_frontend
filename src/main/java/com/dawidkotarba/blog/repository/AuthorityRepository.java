package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import io.vavr.collection.Set;

public interface AuthorityRepository extends BaseRepository<AuthorityEntity> {

    AuthorityEntity findByAuthority(String authority);

    Set<AuthorityEntity> findAllByAuthorityStartingWithIgnoreCase(String username);
}
