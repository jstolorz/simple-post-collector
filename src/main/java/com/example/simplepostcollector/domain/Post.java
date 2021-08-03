package com.example.simplepostcollector.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Data
@Entity
public class Post {
    @Id
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    private String title;
    @Column(length = 1024)
    private String body;
    private boolean edited;
    private boolean deleted;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Post post = (Post) o;
        return id.equals(post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
