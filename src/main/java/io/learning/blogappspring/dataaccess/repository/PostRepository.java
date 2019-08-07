package io.learning.blogappspring.dataaccess.repository;

import io.learning.blogappspring.dataaccess.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
