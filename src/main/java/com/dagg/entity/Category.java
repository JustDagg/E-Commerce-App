package com.dagg.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Dagg
 */

@Entity
@Table(name = "Category")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "category_name")
    private String name;

//    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
//    private List<Product> products;

}
