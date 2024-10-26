package com.rbalazs.stock.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

/**
 * Represents a given Product.
 * Note => to simplify the example the Repository layer is implemented in-memory.
 *
 * @author Rodrigo Balazs
 */
@Getter
@Setter
public class Product {

    private String name;
    private int availableQuantity;

    /**
     * Creates a new Product.
     *
     * @param theName the product name, cannot be null nor empty.
     * @param theAvailableQuantity the product available quantity, cannot be negative.
     */
    public Product(final String theName, final int theAvailableQuantity) {
        Validate.notEmpty(theName, "The product name cannot be null nor empty");
        Validate.isTrue(theAvailableQuantity >= 0, "The available quantity must be greater or equal to zero");
        name = theName;
        availableQuantity = theAvailableQuantity;
    }
}
