package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.LabelConverter;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.repository.CacheableLabelRepository;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class LabelFacade {

    private final CacheableLabelRepository cacheableLabelRepository;
    private final LabelConverter labelConverter;

    @Inject
    public LabelFacade(final CacheableLabelRepository cacheableLabelRepository, final LabelConverter labelConverter) {
        this.cacheableLabelRepository = cacheableLabelRepository;
        this.labelConverter = labelConverter;
    }

    public Set<LabelDto> findAll() {
        final Set<LabelDto> result = cacheableLabelRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(l -> l.getLabel().getName()))
                .map(labelConverter::convert)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);
    }

    public Set<LabelDto> findByName(final String name) {
        Preconditions.checkNotNull(name);
        final Set<LabelDto> result = cacheableLabelRepository.findAll()
                .stream()
                .filter(labelEntity -> StringUtils.containsIgnoreCase(labelEntity.getLabel().getName(), name))
                .sorted(Comparator.comparing(l -> l.getLabel().getName()))
                .map(labelConverter::convert)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        return Collections.unmodifiableSet(result);
    }
}
