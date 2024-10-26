package com.rbalazs.orders.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * Represents a given Order, an Order contains a list of {@link OrderItem}.
 * Note => to simplify the example the Repository layer is implemented in-memory.
 *
 * @author Rodrigo Balazs
 */
@Getter
@Setter
public class Order {

    private Long id;
    private List<OrderItem> items;
}
