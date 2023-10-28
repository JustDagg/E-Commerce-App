package com.dagg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Dagg
 */

@Entity
@Data
@Table(name = "`Role`")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
