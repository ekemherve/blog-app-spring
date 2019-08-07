package io.learning.blogappspring.dataaccess.repository;

import io.learning.blogappspring.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    Collection<UserEntity> findByUsernameOrEmail(String username, String email);

    @Query(value = "select u from UserEntity u where (u.username = ?1 or u.email = ?2) and u.id <> ?3")
    Collection<UserEntity> findByUsernameOrEmailExceptCurrentUser(String username, String email, Long id);
}
