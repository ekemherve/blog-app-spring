package io.learning.blogappspring.dataaccess.mapper;

import io.learning.blogappspring.dataaccess.entity.UserEntity;
import io.learning.blogappspring.model.User;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserEntity toEntity(User user);

    User toModel(UserEntity userEntity);

    Collection<User> toCollection(Collection<UserEntity> users);

    Collection<UserEntity> toEntityCollection(Collection<User> users);
}
