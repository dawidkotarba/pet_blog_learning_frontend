package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findByUsername(String userName);
}
