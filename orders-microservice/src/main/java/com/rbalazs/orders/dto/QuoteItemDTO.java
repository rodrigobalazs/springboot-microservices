package com.rbalazs.orders.dto;

/**
 * Represents a Quote Item.
 *
 * @author Rodrigo Balazs
 */
public class QuoteItemDTO {

    private String productName;
    private int requestedQuantity;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public void setRequestedQuantity(int requestedQuantity) {
        this.requestedQuantity = requestedQuantity;
    }
}
