package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.PostConverter;
import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;
import com.dawidkotarba.blog.model.entities.PostEntity;
import com.dawidkotarba.blog.repository.AuthorRepository;
import com.dawidkotarba.blog.repository.PostRepository;

import javax.inject.Inject;
import javax.inject.Named;
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
        return all.stream()
                .map(entity -> postConverter.convertToDto(entity))
                .collect(Collectors.toList());
    }

    public List<PostDto> findBySubject(final String subject) {
        final List<PostEntity> bySubject = postRepository.findBySubject(subject);
        return bySubject.stream().map(entity -> postConverter.convertToDto(entity)).collect(Collectors.toList());
    }

    public void add(final PostDto postDto) {
        final String username = postDto.getAuthor().getUsername();
        final AuthorEntity authors = authorRepository.findByUsername(username);
        final PostEntity entity = postConverter.convertToEntity(postDto);
        entity.setAuthor(authors);

        postRepository.save(entity);
    }
}
