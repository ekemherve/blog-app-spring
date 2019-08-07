package io.learning.blogappspring.service;

import io.learning.blogappspring.dataaccess.entity.RoleEntity;
import io.learning.blogappspring.dataaccess.mapper.RoleMapper;
import io.learning.blogappspring.dataaccess.repository.RoleRepository;
import io.learning.blogappspring.model.Role;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.learning.blogappspring.model.Constant.*;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }


    @Override
    public Collection<Role> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::toModel).collect(Collectors.toSet());
    }

    @Override
    public Role findById(Long id) /*throws Exception*/ {

        /*if(Objects.isNull(id))
            throw new IllegalArgumentException(ROLE_DOESNT_EXIST);

        Optional<RoleEntity> optionalRoleFromDatabase = roleRepository.findById(id);

        if(!optionalRoleFromDatabase.isPresent())
            throw new Exception(ROLE_NOT_FOUND);*/

        RoleEntity roleFromDatabase = null;

        if(Objects.nonNull(id))
            roleFromDatabase = roleRepository.findById(id).orElse(null);

        return roleMapper.toModel(roleFromDatabase);

    }

    @Override
    public Role save(Role role) throws Exception {

        if(Objects.isNull(role))
            throw new IllegalArgumentException(ROLE_CANNOT_BE_NULL);

        if(Objects.isNull(role.getName()))
            throw new Exception(ROLENAME_CANNOT_BE_NULL);

        RoleEntity roleFound = roleRepository.findByName(role.getName());

        if(Objects.nonNull(roleFound))
            throw new Exception(ROLE_ALREADY_EXISTS);

        RoleEntity roleEntity = roleMapper.toEntity(role);

        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.toModel(roleEntity);
    }

    @Override
    public Role update(Role role) throws Exception {

        if(Objects.isNull(role))
            throw new IllegalArgumentException(ROLE_CANNOT_BE_NULL);

        if(Objects.isNull(role.getName()))
            throw new Exception(ROLENAME_CANNOT_BE_NULL);

        if(Objects.isNull(role.getId()))
            throw new Exception(ROLE_DOESNT_EXIST);

        Optional<RoleEntity> roleFromDatabase = roleRepository.findById(role.getId());

        if(!roleFromDatabase.isPresent())
            throw new Exception(USER_DOESNT_EXIST);

        RoleEntity roleEntity = roleRepository.findByNameAndIdIsNot(role.getName(), role.getId());

        if(Objects.nonNull(roleEntity))
            throw new Exception(ROLE_ALREADY_EXISTS);

        role.setId(roleFromDatabase.get().getId());

        roleEntity = roleMapper.toEntity(role);
        roleEntity = roleRepository.save(roleEntity);

        return roleMapper.toModel(roleEntity);
    }

    @Override
    public void delete(Long id) {

        RoleEntity roleToSave = null;

        if(Objects.nonNull(id))
            roleToSave = roleRepository.findById(id).orElse(null);

        if(Objects.nonNull(roleToSave))
            roleRepository.delete(roleToSave);
    }

    @Override
    public Role createIfNotExists(String roleName) {

        RoleEntity role = roleRepository.findByName(roleName);

        if(Objects.isNull(role)) {
            role = new RoleEntity();
            role.setName("ROLE_USER");
            role = roleRepository.save(role);
        }
        return roleMapper.toModel(role);
    }


}
