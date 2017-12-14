package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.LabelConverter;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.repository.CacheableLabelRepository;

import javax.inject.Inject;
import javax.inject.Named;
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
        return cacheableLabelRepository.findAll().stream().map(labelConverter::convert).collect(Collectors.toSet());
    }
}
