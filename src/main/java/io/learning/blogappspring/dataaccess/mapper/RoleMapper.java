package io.learning.blogappspring.dataaccess.mapper;

import io.learning.blogappspring.dataaccess.entity.RoleEntity;
import io.learning.blogappspring.model.Role;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toModel(RoleEntity roleEntity);

    RoleEntity toEntity(Role role);

    Collection<Role> toCollection(Collection<RoleEntity> roles);

    Collection<RoleEntity> toEntityCollection(Collection<Role> roles);
}
