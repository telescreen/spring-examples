package com.buiha.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "privileges")
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles;

    public Privilege() {
    }

    /**
     * Constructor: create new role by name
     * @param name
     */
    public Privilege(String name) {
        this.name = name;
    }

    /**
     * Return the name of this role
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return all roles with this privilege
     * @return
     */
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
