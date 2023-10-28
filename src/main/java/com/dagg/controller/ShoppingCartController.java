package com.dagg.controller;

import com.dagg.dto.OrderDTO;
import com.dagg.dto.ResponseOrderDTO;
import com.dagg.dto.UserDTO;
import com.dagg.entity.Order;
import com.dagg.entity.Product;
import com.dagg.entity.User;
import com.dagg.service.OrderService;
import com.dagg.service.ProductService;
import com.dagg.service.UserService;
import com.dagg.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;
import java.util.Random;

/**
 * @author Dagg
 * @created 27/10/2023
 */

@RestController
@RequestMapping("api")
public class ShoppingCartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(ShoppingCartController.class);

    @GetMapping("/carts/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> productList = productService.getAllListProducts();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/carts/get-order/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable int orderId) {

        Order order = orderService.getOrderDetail(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/carts/place-order")
    public  ResponseEntity<List<ResponseOrderDTO>> getAllPlaceOrder() {
        List<ResponseOrderDTO> responseOrderDTOList = orderService.getAllListOrders();
        return ResponseEntity.ok(responseOrderDTOList);
    }

    @PostMapping("/carts/place-order")
    public ResponseEntity<ResponseOrderDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Request Payload " + orderDTO.toString());
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO();
        float amount = orderService.getCartAmount(orderDTO.getCartItems());

//        UserDTO userDTO = new UserDTO(orderDTO.getUserDTO().getId(), orderDTO.getUserDTO().getEmail());
//        Integer userIdFromDB = userService.isUserPresent(userDTO);
//        if (userIdFromDB != null) {
//            user.setId(userIdFromDB);
//            logger.info("User already present in db with id : " + userIdFromDB);
//        }else{
//            user = userService.saveUser(user);
//            logger.info("User saved.. with id : " + user.getId());
//        }
//        Order order = new Order(orderDTO.getOrderDescription(), user, orderDTO.getCartItems());
//        order = orderService.saveOrder(order);
//        logger.info("Order processed successfully..");

        responseOrderDTO.setAmount(amount);
        responseOrderDTO.setDate(DateUtil.getCurrentDateTime());
        responseOrderDTO.setInvoiceNumber(new Random().nextInt(1000));
//        responseOrderDTO.setOrderId(order.getId());
        responseOrderDTO.setOrderDescription(orderDTO.getOrderDescription());

        orderService.saveOrder(orderDTO);
        logger.info("test push..");

        return ResponseEntity.ok(responseOrderDTO);
    }

    @DeleteMapping("carts/place-order/delete/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable(name = "id") int id) {
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("Product deleted! please redirect:/carts/place-order", HttpStatus.OK);
    }

}
