package com.rbalazs.orders.service;

import com.rbalazs.orders.dto.QuoteDTO;
import com.rbalazs.orders.dto.QuoteItemDTO;
import com.rbalazs.orders.enums.OrderAppValidations;
import com.rbalazs.orders.exception.OrderCustomException;
import com.rbalazs.orders.model.Order;
import com.rbalazs.orders.model.OrderItem;
import com.rbalazs.orders.repository.OrderRepository;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * Order Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Place(creates) a new Order based on the {@link QuoteDTO} given as parameter.
     *
     * @param quoteDTO the quote.
     */
    public void placeOrder(QuoteDTO quoteDTO) {

        String customerEmail = quoteDTO.getCustomerEmail();
        if (StringUtils.isEmpty(customerEmail)){
            throw new OrderCustomException(OrderAppValidations.CUSTOMER_NOT_FOUND);
        }

        List<QuoteItemDTO> quoteItems = quoteDTO.getItems();
        if (CollectionUtils.isEmpty(quoteItems)){
            throw new OrderCustomException(OrderAppValidations.EMPTY_ORDER_ITEMS);
        }

        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (QuoteItemDTO quoteItem : quoteItems) {
            // TODO(rodrigo.balazs) check whether the quote item is in stock or not calling Stock Microservice ..
            OrderItem orderItem = new OrderItem(quoteItem.getProductName(), quoteItem.getRequestedQuantity());
            orderItems.add(orderItem);
        }

        Order order = new Order(customerEmail, orderItems);
        orderRepository.save(order);
        // TODO(rodrigo.balazs) send a new order creation notification via the Notifications microservice ..
    }
}
