package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.dto.PostDto;
import com.dawidkotarba.blog.model.entities.PostEntity;
import com.dawidkotarba.blog.repository.PostRepository;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.List;

@Named
public class PostFacade {

    private final PostRepository postRepository;

    @Inject
    public PostFacade(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDto> findAll() {
        final List<PostEntity> all = postRepository.findAll();
        return Collections.emptyList();
    }
}
