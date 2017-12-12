package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.CommentInConverter;
import com.dawidkotarba.blog.converters.impl.CommentOutConverter;
import com.dawidkotarba.blog.model.dto.impl.CommentInDto;
import com.dawidkotarba.blog.model.dto.impl.CommentOutDto;
import com.dawidkotarba.blog.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CommentFacade {

    private final CommentRepository commentRepository;
    private final CommentInConverter commentInConverter;
    private final CommentOutConverter commentOutConverter;

    @Inject
    public CommentFacade(final CommentRepository commentRepository, final CommentInConverter commentInConverter,
                         final CommentOutConverter commentOutConverter) {
        this.commentRepository = commentRepository;
        this.commentInConverter = commentInConverter;
        this.commentOutConverter = commentOutConverter;
    }

    public void add(final CommentInDto commentInDto) {
        commentRepository.save(commentInConverter.convert(commentInDto));
    }

    public Page<CommentOutDto> findByPostId(final Long postId, final Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable).map(commentOutConverter::convert);
    }
}
