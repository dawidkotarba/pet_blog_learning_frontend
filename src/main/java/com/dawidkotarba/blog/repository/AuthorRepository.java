package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByUsername(String userName);
}
