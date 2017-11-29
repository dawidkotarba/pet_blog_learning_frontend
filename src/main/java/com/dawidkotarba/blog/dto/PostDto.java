package com.dawidkotarba.blog.dto;

import com.dawidkotarba.blog.model.entities.AuthorEntity;
import com.dawidkotarba.blog.model.entities.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto extends AbstractDto {
    private String subject;
    private String body;
    private Date published;
    private AuthorEntity author;
    private List<CommentEntity> comments;
}
