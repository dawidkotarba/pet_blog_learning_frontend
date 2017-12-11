package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.auth.annotations.AuthorizeAuthorities;
import com.dawidkotarba.blog.auth.enums.UserAuthority;
import com.dawidkotarba.blog.converters.impl.PostInConverter;
import com.dawidkotarba.blog.converters.impl.PostOutConverter;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.AuthorRepository;
import com.dawidkotarba.blog.repository.PostRepository;
import com.google.common.base.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class PostFacade {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    private final PostOutConverter postOutConverter;
    private final PostInConverter postInConverter;

    @Inject
    PostFacade(final PostRepository postRepository, final AuthorRepository authorRepository,
               final PostOutConverter postOutConverter, final PostInConverter postInConverter) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
        this.postOutConverter = postOutConverter;
        this.postInConverter = postInConverter;
    }

    public List<PostOutDto> findAll() {
        final List<PostEntity> all = postRepository.findAll();
        final List<PostOutDto> result = all.stream()
                .map(postOutConverter::convert)
                .collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    public Optional<List<PostOutDto>> findBySubject(final String subject) {
        Preconditions.checkNotNull(subject);
        final List<PostEntity> bySubject = postRepository.findBySubject(subject);

        if (Objects.isNull(bySubject) || bySubject.isEmpty()) {
            return Optional.empty();
        }

        final List<PostOutDto> result = bySubject.stream().map(postOutConverter::convert).collect(Collectors.toList());
        return Optional.of(result);
    }

    public List<PostOutDto> findMontlyByDayOfAMonth(final LocalDate dayOfAMonth) {
        Preconditions.checkNotNull(dayOfAMonth);
        final LocalDate startDate = dayOfAMonth.withDayOfMonth(1);
        final LocalDate endDate = dayOfAMonth.withDayOfMonth(dayOfAMonth.lengthOfMonth());
        return findFromDateToDate(LocalDateTime.of(startDate, LocalTime.MIDNIGHT), LocalDateTime.of(endDate, LocalTime.MAX));
    }

    public List<PostOutDto> findFromDateToDate(final LocalDateTime fromDate, final LocalDateTime toDate) {
        Preconditions.checkNotNull(fromDate);
        Preconditions.checkNotNull(toDate);
        final List<PostEntity> bySubject = postRepository.findByPublishedBetween(Timestamp.valueOf(fromDate), Timestamp.valueOf(toDate));
        final List<PostOutDto> result = bySubject.stream().map(postOutConverter::convert).collect(Collectors.toList());
        return Collections.unmodifiableList(result);
    }

    @AuthorizeAuthorities(authorities = {UserAuthority.ADMINISTRATE, UserAuthority.WRITE})
    public void add(final PostInDto postInDto) {
        Preconditions.checkNotNull(postInDto);
        final List<AuthorEntity> authors = authorRepository.findByIds(postInDto.getAuthors());
        final PostEntity entity = postInConverter.convert(postInDto);
        entity.setAuthors(new HashSet<>(authors));
        postRepository.save(entity);
    }
}
