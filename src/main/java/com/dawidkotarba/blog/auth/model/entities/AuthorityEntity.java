package com.dawidkotarba.blog.auth.model.entities;

import com.dawidkotarba.blog.model.entities.impl.AbstractEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AUTHORITIES")
@SequenceGenerator(name = "PK", sequenceName = "AUTHORITIES_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorityEntity extends AbstractEntity implements GrantedAuthority {
    private String authority;
}
