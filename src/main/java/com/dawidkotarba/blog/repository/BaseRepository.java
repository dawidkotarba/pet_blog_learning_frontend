package com.dawidkotarba.blog.repository;

import com.dawidkotarba.blog.model.entities.impl.AbstractEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {

    String UUID = "uuid";

    default <S extends T> Optional<S> findOne(final S example) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths(UUID);
        final Example<S> wrappedExample = Example.of(example, exampleMatcher);
        return findOne(wrappedExample);
    }

    default <S extends T> List<S> findAll(final S example) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths(UUID);
        final Example<S> wrappedExample = Example.of(example, exampleMatcher);
        return findAll(wrappedExample);
    }

    default <S extends T> Page<S> findAll(final S example, final Pageable pageable) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths(UUID);
        final Example<S> wrappedExample = Example.of(example, exampleMatcher);
        return findAll(wrappedExample, pageable);
    }

    default <S extends T> long count(final S example) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths(UUID);
        final Example<S> wrappedExample = Example.of(example, exampleMatcher);
        return count(wrappedExample);
    }

    default <S extends T> boolean exists(final S example) {
        final ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths(UUID);
        final Example<S> wrappedExample = Example.of(example, exampleMatcher);
        return exists(wrappedExample);
    }
}
