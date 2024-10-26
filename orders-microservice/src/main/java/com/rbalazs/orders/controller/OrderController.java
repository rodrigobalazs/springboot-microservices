package com.rbalazs.orders.controller;

import com.rbalazs.orders.model.Order;
import com.rbalazs.orders.service.OrderService;
import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Order REST Controller.
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        LOGGER.info("starts to execute orderController.createOrder()");
        orderService.createOrder(order);
        return ResponseEntity.ok("a new Order has been successfully created");
    }
}

