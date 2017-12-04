package com.dawidkotarba.blog.model.entities;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements IdentifiableEntity {

    @Id
    @GeneratedValue(generator = "PK", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    private Integer version;

    @Column(nullable = false, unique = true)
    private String uuid;

    public AbstractEntity() {
        uuid = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractEntity)) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        return getUuid().equals(other.getUuid());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public UUID getUuid() {
        return UUID.fromString(uuid);
    }
}
