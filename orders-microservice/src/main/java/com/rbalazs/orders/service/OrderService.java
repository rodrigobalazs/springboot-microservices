package com.rbalazs.orders.service;

import com.rbalazs.orders.model.Order;
import com.rbalazs.orders.model.OrderItem;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Order Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class OrderService {

    /**
     * Creates a new Order based on the {@link Order} given as parameter.
     *
     * @param order the Order to create.
     */
    public void createOrder(Order order) {

        List<Order> orders = new ArrayList<>();
        for (OrderItem item : order.getItems()) {
            // TODO(rodrigo.balazs) check whether the order item is in stock or not calling Stock Microservice ..
        }
        orders.add(order);
        // TODO(rodrigo.balazs) send a new order creation notification via the Notifications microservice ..
    }
}
