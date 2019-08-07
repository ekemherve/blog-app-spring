package io.learning.blogappspring.service;

import io.learning.blogappspring.model.Role;

import java.util.Collection;

public interface RoleService {

    Collection<Role> findAll();

    Role findById(Long id)/* throws Exception*/;

    Role save(Role role) throws Exception;

    Role update(Role role) throws Exception;

    void delete(Long id);

    Role createIfNotExists(String roleName);
}
