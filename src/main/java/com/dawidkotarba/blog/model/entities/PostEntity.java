package com.dawidkotarba.blog.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "POSTS")
@SequenceGenerator(name = "PK", sequenceName = "POSTS_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String subject;

    @Lob
    private String body;

    private Date published;

    @OneToOne
    @JoinColumn(name = "AUTHOR_ID")
    private AuthorEntity author;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<CommentEntity> comments;
}
