package com.dawidkotarba.blog.model.entities.impl;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private LocalDateTime published;

    @ManyToMany
    @JoinTable(name = "POSTS_AUTHORS", joinColumns = @JoinColumn(name = "POST_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    @JoinColumn(name = "AUTHOR_ID")
    private Set<AuthorEntity> authors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private Set<CommentEntity> comments;

    @ManyToMany
    @JoinTable(name = "POSTS_LABELS", joinColumns = @JoinColumn(name = "POST_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "LABEL_ID", referencedColumnName = "ID"))
    @JoinColumn(name = "LABEL_ID")
    private Set<LabelEntity> labels;
}
