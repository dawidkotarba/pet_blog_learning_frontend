package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    AuthorEntity findByUsername(String username);

    @Query("SELECT a from AuthorEntity a WHERE id in :ids")
    Set<AuthorEntity> findByIds(@Param("ids") Set<Long> ids);
}
