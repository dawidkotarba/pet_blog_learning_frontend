package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.dto.CommentDto;
import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.model.entities.AuthorEntity;
import com.dawidkotarba.blog.model.entities.PostEntity;
import com.dawidkotarba.blog.repository.AuthorRepository;
import com.dawidkotarba.blog.repository.PostRepository;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class PostFacade {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    @Inject
    public PostFacade(final PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }

    public List<PostDto> findAll() {
        final List<PostEntity> all = postRepository.findAll();
        return all.stream().map(entity -> {
            CommentDto commentDto = new CommentDto();
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(entity, postDto);
            return postDto;
        }).collect(Collectors.toList());
    }

    public List<PostDto> findBySubject(String subject) {
        List<PostEntity> bySubject = postRepository.findBySubject(subject);
        return bySubject.stream().map(entity -> {
            PostDto postDto = new PostDto();
            BeanUtils.copyProperties(entity, postDto);
            return postDto;
        }).collect(Collectors.toList());
    }

    public void add(PostDto postDto) {
        String username = postDto.getAuthor().getUsername();
        List<AuthorEntity> authors = authorRepository.findByUsername(username);
        PostEntity entity = new PostEntity();
        entity.setAuthor(authors.get(0));
        entity.setBody(postDto.getBody());
        entity.setPublished(postDto.getPublished());
        entity.setSubject(postDto.getSubject());
        postRepository.save(entity);
    }
}
