package com.rbalazs.orders.controller;

import com.rbalazs.orders.dto.QuoteDTO;
import com.rbalazs.orders.service.OrderService;
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
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<String> placeOrder(@RequestBody QuoteDTO quoteDTO) {
        LOGGER.info("starts to execute orderController.placeOrder()");
        orderService.placeOrder(quoteDTO);
        return ResponseEntity.ok("a new Order has been successfully created");
    }
}

