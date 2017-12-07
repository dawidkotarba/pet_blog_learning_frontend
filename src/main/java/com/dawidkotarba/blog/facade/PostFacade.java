package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.PostInConverter;
import com.dawidkotarba.blog.converters.PostOutConverter;
import com.dawidkotarba.blog.model.dto.AuthorDto;
import com.dawidkotarba.blog.model.dto.PostInDto;
import com.dawidkotarba.blog.model.dto.PostOutDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;
import com.dawidkotarba.blog.model.entities.PostEntity;
import com.dawidkotarba.blog.repository.AuthorRepository;
import com.dawidkotarba.blog.repository.PostRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class PostFacade {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostOutConverter postOutConverter;
    private final PostInConverter postInConverter;

    @Inject
    public PostFacade(final PostRepository postRepository, final AuthorRepository authorRepository,
                      final PostOutConverter postOutConverter, final PostInConverter postInConverter) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postOutConverter = postOutConverter;
        this.postInConverter = postInConverter;
    }

    public List<PostOutDto> findAll() {
        final List<PostEntity> all = postRepository.findAll();
        final List<PostOutDto> result = all.stream()
                .map(postOutConverter::convertToDto)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    public List<PostOutDto> findBySubject(final String subject) {
        Preconditions.checkNotNull(subject);
        final List<PostEntity> bySubject = postRepository.findBySubject(subject);
        final List<PostOutDto> result = bySubject.stream().map(postOutConverter::convertToDto).collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    public void add(final PostInDto postInDto) {
        Preconditions.checkNotNull(postInDto);
        final List<AuthorEntity> authors = authorRepository.findByUsernames(postInDto.getAuthors()
                .stream()
                .map(AuthorDto::getUsername)
                .collect(Collectors.toSet()));
        final PostEntity entity = postInConverter.convertToEntity(postInDto);
        entity.setAuthors(new HashSet<>(authors));
        postRepository.save(entity);
    }
}
