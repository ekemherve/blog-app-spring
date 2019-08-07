package io.learning.blogappspring.service;

import io.learning.blogappspring.model.Post;
import io.learning.blogappspring.model.Post;

import java.util.Collection;

public interface PostService {

    Collection<Post> findAll();

    Post findById(Long id) throws Exception/* throws Exception*/;

    Post save(Post post) throws Exception;

    Post update(Post post) throws Exception;

    void delete(Long id);
}
