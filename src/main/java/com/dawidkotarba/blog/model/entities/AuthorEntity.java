package com.dawidkotarba.blog.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
