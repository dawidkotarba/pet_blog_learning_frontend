package com.dawidkotarba.blog.facade;

import com.dawidkotarba.blog.converters.impl.LabelConverter;
import com.dawidkotarba.blog.model.dto.impl.LabelDto;
import com.dawidkotarba.blog.repository.CacheableLabelRepository;
import io.vavr.collection.Set;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Preconditions;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Comparator;

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
        return cacheableLabelRepository.findAllSeq()
                .sorted(Comparator.comparing(l -> l.getLabel().getName()))
                .map(labelConverter::convert)
                .toSet();
    }

    public Set<LabelDto> findByName(final String name) {
        Preconditions.checkNotNull(name);
        return cacheableLabelRepository.findAllSeq()
                .filter(labelEntity -> StringUtils.containsIgnoreCase(labelEntity.getLabel().getName(), name))
                .sorted(Comparator.comparing(l -> l.getLabel().getName()))
                .map(labelConverter::convert)
                .toSet();
    }
}
