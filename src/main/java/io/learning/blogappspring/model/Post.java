package io.learning.blogappspring.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Post {

    private Long id;
    private String title;
    private User creator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
