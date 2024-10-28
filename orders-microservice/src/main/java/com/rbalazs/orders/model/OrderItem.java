package com.rbalazs.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

/**
 * Represents a given Order Item,an OrderItem it´s associated to an specific {@link Order}.
 *
 * @author Rodrigo Balazs
 */
@Getter
@Setter
@Entity
public class OrderItem extends Base {

    private String productName;
    private int requestedQuantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    /* This JSON annotation avoids during GET API Calls this exception =>
    "org.springframework.http.converter.HttpMessageNotWritableException: Could not write JSON: Document nesting depth
    exceeds the maximum allowed" which is caused by a circular dependency between Order and OrderItem */
    @JsonIgnore
    private Order order;

    /** Empty Constructor required by JPA / Hibernate. */
    public OrderItem() {}

    /**
     * Creates a new Order Item.
     *
     * @param theProductName the product name, cannot be null nor empty.
     * @param theRequestedQuantity the requested quantity, cannot be negative or zero.
     */
    public OrderItem(final String theProductName, final int theRequestedQuantity) {
        Validate.notEmpty(theProductName, "The product name cannot be null nor empty");
        Validate.isTrue(theRequestedQuantity > 0, "The requested quantity must be greater to zero");
        productName = theProductName;
        requestedQuantity = theRequestedQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderItem orderItem = (OrderItem) o;
        return requestedQuantity == orderItem.requestedQuantity && Objects.equals(productName, orderItem.productName)
                && Objects.equals(order, orderItem.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), productName, requestedQuantity, order);
    }

    @Override
    public String toString() {
        return "OrderItem{" + "productName='" + productName + '\'' + ", requestedQuantity=" + requestedQuantity
                + ", order=" + order + '}';
    }
}