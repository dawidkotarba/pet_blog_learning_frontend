package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.PostInConverter;
import com.dawidkotarba.blog.converters.impl.PostOutConverter;
import com.dawidkotarba.blog.exceptions.NotFoundException;
import com.dawidkotarba.blog.model.dto.impl.PostInDto;
import com.dawidkotarba.blog.model.dto.impl.PostOutDto;
import com.dawidkotarba.blog.model.entities.impl.AuthorEntity;
import com.dawidkotarba.blog.model.entities.impl.PostEntity;
import com.dawidkotarba.blog.repository.CacheableAuthorRepository;
import com.dawidkotarba.blog.repository.CacheablePostRepository;
import com.google.common.base.Preconditions;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class PostFacade {

    private final CacheablePostRepository cacheablePostRepository;
    private final CacheableAuthorRepository cacheableAuthorRepository;
    private final PostOutConverter postOutConverter;
    private final PostInConverter postInConverter;

    @Inject
    PostFacade(final CacheablePostRepository cacheablePostRepository, final CacheableAuthorRepository
            cacheableAuthorRepository,
               final PostOutConverter postOutConverter, final PostInConverter postInConverter) {
        this.cacheablePostRepository = cacheablePostRepository;
        this.cacheableAuthorRepository = cacheableAuthorRepository;
        this.postOutConverter = postOutConverter;
        this.postInConverter = postInConverter;
    }

    public Set<PostOutDto> findAll() {
        final LinkedHashSet<PostOutDto> result = cacheablePostRepository.findAll()
                .stream()
                .map(postOutConverter::convert)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);
    }

    public Page<PostOutDto> findAll(final Pageable pageable) {
        return cacheablePostRepository.findAll(pageable).map(postOutConverter::convert);
    }

    public Option<PostOutDto> findBySubject(final String subject) {
        Preconditions.checkNotNull(subject);
        final PostEntity bySubject = cacheablePostRepository.findBySubject(subject);
        if (Objects.isNull(bySubject)) {
            return Option.none();
        }
        return Option.some(postOutConverter.convert(bySubject));
    }

    public PostOutDto findById(final Long id) {
        Preconditions.checkNotNull(id);
        final PostEntity post = cacheablePostRepository.findById(id).get();
        return postOutConverter.convert(post);
    }

    public Set<PostOutDto> findMonthlyByDayOfAMonth(final LocalDate dayOfAMonth) {
        Preconditions.checkNotNull(dayOfAMonth);
        final LocalDate startDate = dayOfAMonth.withDayOfMonth(1);
        final LocalDate endDate = dayOfAMonth.withDayOfMonth(dayOfAMonth.lengthOfMonth());
        return findFromDateToDate(LocalDateTime.of(startDate, LocalTime.MIDNIGHT), LocalDateTime.of(endDate,
                LocalTime.MAX));
    }

    public Set<PostOutDto> findFromDateToDate(final LocalDateTime fromDate, final LocalDateTime toDate) {
        Preconditions.checkNotNull(fromDate);
        Preconditions.checkNotNull(toDate);
        final Set<PostEntity> bySubject = cacheablePostRepository.findByPublishedBetween(fromDate, toDate);
        final Set<PostOutDto> result = bySubject.stream()
                .map(postOutConverter::convert)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);
    }

    @PreAuthorize("hasAuthority('administrate') or hasAuthority('write')")
    public void add(final PostInDto postInDto) {
        Preconditions.checkNotNull(postInDto);
        final List<AuthorEntity> authors = getAuthors(postInDto);
        final PostEntity entity = postInConverter.convert(postInDto);
        entity.setAuthors(new HashSet<>(authors));
        cacheablePostRepository.save(entity);
    }

    private List<AuthorEntity> getAuthors(final PostInDto postInDto) {
        final Set<Long> authorIds = postInDto.getAuthors();
        final List<AuthorEntity> authors = cacheableAuthorRepository.findAllById(authorIds);
        if (authors.isEmpty()) {
            throw new NotFoundException(
                    "Author(s) " + authorIds + " not found. Please add valid author(s).");
        }
        return authors;
    }
}
