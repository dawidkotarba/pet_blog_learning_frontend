package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;

import java.util.List;

public interface AuthorityRepository extends BaseRepository<AuthorityEntity> {

    AuthorityEntity findByAuthority(String authority);

    List<AuthorityEntity> findAllByAuthorityStartingWithIgnoreCase(String username);
}
