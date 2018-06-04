package com.buiha.models;

import javax.persistence.*;
import java.util.Set;

@Table(name = "roles")
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private Set<Privilege> privileges;

    public Role() {
    }

    /**
     * Constructor: create a Role by name
     * @param name
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Return name of role
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return a Collection of privileges this role has
     * @return
     */
    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    /**
     * Set privileges for this role
     * @param privileges
     */
    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
