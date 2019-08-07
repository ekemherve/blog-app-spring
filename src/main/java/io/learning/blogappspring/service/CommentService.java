package io.learning.blogappspring.service;

import io.learning.blogappspring.model.Comment;

import java.util.Collection;

public interface CommentService {

    Collection<Comment> findAll();

    Comment findById(Long id) throws Exception/* throws Exception*/;

    Comment save(Comment comment) throws Exception;

    Comment update(Comment comment) throws Exception;

    void delete(Long id);
}
