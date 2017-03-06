package com.buiha.model;

import javax.persistence.*;

/**
 * Created by tal on 3/1/17.
 */

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne
    @JoinColumn(name = "userid", foreignKey = @ForeignKey(name = "roles_userid_fkey"))
    private User user;

    public Role() {
    }

    public Role(String role, User user) {
        this.role = role;
        this.user = user;
    }

    public String getRole() {
        return role;
    }
}
