package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import com.dawidkotarba.blog.model.dto.OutDto;
import io.vavr.collection.Set;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LabelDto implements InDto, OutDto {

    @NotNull
    private Long id;
    @NotBlank
    private String label;
    @NotNull
    private Set<Long> posts;
}
