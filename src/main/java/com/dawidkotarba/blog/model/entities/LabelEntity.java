package com.dawidkotarba.blog.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LABELS")
@SequenceGenerator(name = "PK", sequenceName = "LABELS_SEQ", allocationSize = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelEntity extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "POSTS_LABELS", inverseJoinColumns = @JoinColumn(name = "POST_ID", referencedColumnName = "ID"),
            joinColumns = @JoinColumn(name = "LABEL_ID", referencedColumnName = "ID"))
    @JoinColumn(name = "LABEL_ID")
    private Set<PostEntity> posts;
}
