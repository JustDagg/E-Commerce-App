package com.dagg.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Entity
@Data
@Table(name = "`Order`")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String orderDescription;

//    @OneToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
    private List<ShoppingCart> cartItems;

    // Custom lai constructor
//    public Order(String orderDescription, User user, List<ShoppingCart> cartItems) {
//        this.orderDescription = orderDescription;
//        this.user = user;
//        this.cartItems = cartItems;
//    }
}
