package com.dagg.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Dagg
 */

@Entity
@Table(name = "Product")
@Data
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "product_name", length = 50)
    private String name;

    @Column(name = "available_quantity")
    private int availableQuantity;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "image_name", length = 5000)
    private String imageName;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

}
