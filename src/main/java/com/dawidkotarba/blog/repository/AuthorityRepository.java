package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    AuthorityEntity findByAuthority(String authority);

    List<AuthorityEntity> findAllByAuthorityStartingWithIgnoreCase(String username);
}
