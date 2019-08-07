package io.learning.blogappspring.service;

import io.learning.blogappspring.model.User;

import java.util.Collection;

public interface UserService {

    Collection<User> findAll();

    User findById(Long id) throws Exception;

    User save(User user) throws Exception;

    User update(User user) throws Exception;

    void delete(Long id);

}
