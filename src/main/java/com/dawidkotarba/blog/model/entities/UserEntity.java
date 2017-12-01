package com.dawidkotarba.blog.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@SequenceGenerator(name = "PK", sequenceName = "USERS_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String username;

    private String firstname;

    private String lastname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @Column(nullable = false)
    private String role;


}
