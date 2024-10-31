package com.rbalazs.orders.dto;

import lombok.Data;

/**
 * Represents a Quote Item.
 *
 * @author Rodrigo Balazs
 */
@Data
public class QuoteItemDTO {

    private String productName;
    private int requestedQuantity;
}
