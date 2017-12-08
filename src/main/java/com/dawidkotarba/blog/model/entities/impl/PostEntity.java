package com.dawidkotarba.blog.model.entities.impl;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

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

    private Timestamp published;

    @ManyToMany
    @JoinTable(name = "POSTS_AUTHORS", joinColumns = @JoinColumn(name = "POST_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    @JoinColumn(name = "AUTHOR_ID")
    private Set<AuthorEntity> authors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<CommentEntity> comments;
}
