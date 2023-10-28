package com.dagg.dto;

import com.dagg.entity.ShoppingCart;
import com.dagg.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Data
public class OrderDTO {

    private String orderDescription;

    private List<ShoppingCart> cartItems;

//    private UserDTO userDTO;

}
