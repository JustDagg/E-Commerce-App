package com.dagg.service;

import com.dagg.dto.OrderDTO;
import com.dagg.dto.ResponseOrderDTO;
import com.dagg.entity.Order;
import com.dagg.entity.ShoppingCart;

import java.util.List;


/**
 * @author Dagg
 * @created 27/10/2023
 */

public interface OrderService {

    Order getOrderDetail(int orderId);

    float getCartAmount(List<ShoppingCart> shoppingCartList);

    void saveOrder(OrderDTO orderDTO);

    void deleteOrderById(int id);

    List<ResponseOrderDTO> getAllListOrders();
}
