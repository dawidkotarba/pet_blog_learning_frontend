package com.dawidkotarba.blog.model.dto.impl;

import com.dawidkotarba.blog.model.dto.InDto;
import com.dawidkotarba.blog.model.dto.OutDto;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

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
