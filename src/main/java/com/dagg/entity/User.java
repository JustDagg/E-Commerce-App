package com.dagg.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Dagg
 */

@Entity
@Data
@Table(name = "`User`")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "`name`", length = 30, nullable = false)
    private String name;

    @Column(name = "username", length = 30, nullable = false)
    private String username;

    @Column(name = "`password`", length = 800)
    private String password;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "`status`", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserStatus status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "`User_Role`",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
    )
    private List<Role> roles;

    public enum UserStatus {
        NOT_ACTIVE, ACTIVE;
    }

    public User() {
    }

}
