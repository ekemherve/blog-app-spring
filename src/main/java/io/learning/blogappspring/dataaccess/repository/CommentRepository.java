package io.learning.blogappspring.dataaccess.repository;

import io.learning.blogappspring.dataaccess.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
