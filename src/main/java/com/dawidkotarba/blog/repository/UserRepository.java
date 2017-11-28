package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Dawid Kotarba on 17.01.2016.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String name);
}
