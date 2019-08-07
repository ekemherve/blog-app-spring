package io.learning.blogappspring.dataaccess.mapper;

import io.learning.blogappspring.dataaccess.entity.CommentEntity;
import io.learning.blogappspring.model.Comment;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring",uses = {PostMapper.class, UserMapper.class})
public interface CommentMapper {

    Comment toModel(CommentEntity postEntity);

    CommentEntity toEntity(Comment comment);

    Collection<Comment> toCollection(Collection<CommentEntity> posts);

    Collection<CommentEntity> toEntityCollection(Collection<Comment> comments);
}
