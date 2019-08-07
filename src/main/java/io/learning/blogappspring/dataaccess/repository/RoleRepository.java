package io.learning.blogappspring.dataaccess.repository;

import io.learning.blogappspring.dataaccess.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);

    RoleEntity findByNameAndIdIsNot(String name, Long id);
}
