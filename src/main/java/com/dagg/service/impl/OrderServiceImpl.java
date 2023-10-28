package com.dagg.service.impl;

import com.dagg.dto.OrderDTO;
import com.dagg.dto.ResponseOrderDTO;
import com.dagg.entity.Order;
import com.dagg.entity.Product;
import com.dagg.entity.ShoppingCart;
import com.dagg.entity.User;
import com.dagg.repository.OrderRepository;
import com.dagg.repository.ProductRepository;
import com.dagg.service.OrderService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Order getOrderDetail(int orderId) {
        Optional<Order> order = this.orderRepository.findById(orderId);
        return order.isPresent() ? order.get() : null;
    }

    @Override
    public float getCartAmount(List<ShoppingCart> shoppingCartList) {
        float totalCartAmount = 0f;
        float singleCartAmount = 0f;
        int availableQuantity = 0;

        for (ShoppingCart cart : shoppingCartList) {

            int productId = cart.getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if (product.isPresent()) {
                Product product1 = product.get();
                if (product1.getAvailableQuantity() < cart.getQuantity()) {
                    singleCartAmount = (float) (product1.getPrice() * product1.getAvailableQuantity());
                    cart.setQuantity(product1.getAvailableQuantity());
                } else {
                    singleCartAmount = (float) (cart.getQuantity() * product1.getPrice());
                    availableQuantity = product1.getAvailableQuantity() - cart.getQuantity();
                }
                totalCartAmount = totalCartAmount + singleCartAmount;
                product1.setAvailableQuantity(availableQuantity);
                availableQuantity=0;
                cart.setProductName(product1.getName());
                cart.setAmount(singleCartAmount);
                productRepository.save(product1);
            }
        }
        return totalCartAmount;
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        Order order1 = orderRepository.save(order);
        System.out.println(order);
        System.out.println(order1);
    }

    @Override
    public void deleteOrderById(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public List<ResponseOrderDTO> getAllListOrders() {
        List<Order> orders = orderRepository.findAll();
        List<ResponseOrderDTO> responseOrderDTOS = modelMapper.map(orders, new TypeToken<List<ResponseOrderDTO>>() {}.getType());
        return responseOrderDTOS;
    }
}
