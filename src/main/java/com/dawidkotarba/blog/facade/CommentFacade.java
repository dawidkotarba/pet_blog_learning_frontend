package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.CommentInConverter;
import com.dawidkotarba.blog.model.dto.impl.CommentInDto;
import com.dawidkotarba.blog.repository.CommentRepository;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommentFacade {

    private final CommentRepository commentRepository;
    private final CommentInConverter commentInConverter;

    @Inject
    public CommentFacade(final CommentRepository commentRepository, final CommentInConverter commentInConverter) {
        this.commentRepository = commentRepository;
        this.commentInConverter = commentInConverter;
    }

    public void add(final CommentInDto commentInDto) {
        commentRepository.save(commentInConverter.convert(commentInDto));
    }
}
