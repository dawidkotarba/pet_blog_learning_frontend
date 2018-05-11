package com.dawidkotarba.blog.model.entities.impl;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
@SequenceGenerator(name = "PK", sequenceName = "AUTHORS_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String username;

    private String firstname;

    private String lastname;

    @ManyToMany
    @JoinTable(name = "POSTS_AUTHORS", inverseJoinColumns = @JoinColumn(name = "POST_ID", referencedColumnName = "ID"),
            joinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID"))
    @JoinColumn(name = "AUTHOR_ID")
    private java.util.Set<PostEntity> posts;
}
