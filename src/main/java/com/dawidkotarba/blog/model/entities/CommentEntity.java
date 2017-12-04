package com.dawidkotarba.blog.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
@SequenceGenerator(name = "PK", sequenceName = "COMMENTS_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentEntity extends AbstractEntity {

    private String author;

    private String subject;

    @Lob
    private String body;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private PostEntity post;

    private Date published;
}
