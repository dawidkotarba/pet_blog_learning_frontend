package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.auth.model.entities.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {

    AuthorityEntity findByAuthority(String authority);
}
