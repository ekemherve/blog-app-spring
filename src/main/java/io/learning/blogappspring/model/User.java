package io.learning.blogappspring.model;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private String birthDay;
    private boolean enabled;
    private boolean credentialsNonExpired;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private Collection<Role> roles;

    public void addRole(Role role) {

        if(Objects.isNull(roles))
            roles = new HashSet<>();
        roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
