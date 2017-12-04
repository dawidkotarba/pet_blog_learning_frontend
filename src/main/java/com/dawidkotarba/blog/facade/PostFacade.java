package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.PostConverter;
import com.dawidkotarba.blog.model.dto.PostDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;
import com.dawidkotarba.blog.model.entities.PostEntity;
import com.dawidkotarba.blog.repository.AuthorRepository;
import com.dawidkotarba.blog.repository.PostRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class PostFacade {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostConverter postConverter;

    @Inject
    public PostFacade(final PostRepository postRepository, final AuthorRepository authorRepository,
                      final PostConverter postConverter) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postConverter = postConverter;
    }

    public List<PostDto> findAll() {
        final List<PostEntity> all = postRepository.findAll();
        final List<PostDto> result = all.stream()
                .map(postConverter::convertToDto)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    public List<PostDto> findBySubject(final String subject) {
        Preconditions.checkNotNull(subject);
        final List<PostEntity> bySubject = postRepository.findBySubject(subject);
        final List<PostDto> result = bySubject.stream().map(postConverter::convertToDto).collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    public void add(final PostDto postDto) {
        Preconditions.checkNotNull(postDto);
        final String username = postDto.getAuthor().getUsername();
        final AuthorEntity authors = authorRepository.findByUsername(username);
        final PostEntity entity = postConverter.convertToEntity(postDto);
        entity.setAuthor(authors);

        postRepository.save(entity);
    }
}
