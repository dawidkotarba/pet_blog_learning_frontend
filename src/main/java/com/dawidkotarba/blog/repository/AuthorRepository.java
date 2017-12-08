package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findByUsername(String username);

    @Query("SELECT a from AuthorEntity a WHERE username in :usernames")
    List<AuthorEntity> findByUsernames(@Param("usernames") Set<String> usernames);
}
