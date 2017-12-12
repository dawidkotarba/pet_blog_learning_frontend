package com.dawidkotarba.blog.repository.cached;

import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.PostRepository;
import org.springframework.cache.annotation.Cacheable;

import javax.inject.Named;
import java.util.List;

@Named
public class CacheablePostRepository {

    private final PostRepository postRepository;

    public CacheablePostRepository(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Cacheable("postsCache")
    public List<PostEntity> findAll() {
        return postRepository.findAll();
    }
}
