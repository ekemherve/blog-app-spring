package io.learning.blogappspring.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Comment {

    private Long id;
    private String text;
    private Boolean isRead;
    private String sentAt;
    private User author;
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
