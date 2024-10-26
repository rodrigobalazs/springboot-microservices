package com.rbalazs.orders.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

/**
 * Represents a given Order Item.
 * Note => to simplify the example the Repository layer is implemented in-memory.
 *
 * @author Rodrigo Balazs
 */
@Getter
@Setter
public class OrderItem {

    private String productName;
    private int requestedQuantity;
}
