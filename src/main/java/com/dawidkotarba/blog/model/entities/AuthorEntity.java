package com.dawidkotarba.blog.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
@SequenceGenerator(name = "PK", sequenceName = "AUTHORS_SEQ", allocationSize = 1)
@Getter
@Setter
public class AuthorEntity extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String username;

    private String firstname;

    private String lastname;
}
