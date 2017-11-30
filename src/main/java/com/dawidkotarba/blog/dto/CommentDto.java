package com.dawidkotarba.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto extends AbstractDto {
    private String author;
    private String subject;
    private String body;
}
