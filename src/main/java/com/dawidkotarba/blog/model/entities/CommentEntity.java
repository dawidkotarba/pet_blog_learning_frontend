package com.dawidkotarba.blog.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
@SequenceGenerator(name = "PK", sequenceName = "COMMENTS_SEQ", allocationSize = 1)
@Getter
@Setter
public class CommentEntity extends AbstractEntity {

    private String author;

    private String subject;

    @Lob
    private String body;

    @ManyToOne
    private PostEntity post;
}
