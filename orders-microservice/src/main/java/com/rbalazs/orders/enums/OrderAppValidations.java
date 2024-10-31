package com.rbalazs.orders.enums;

import org.springframework.http.HttpStatus;

/**
 * Enum which contains some validation messages related to the Orders app/microservice.
 *
 * @author Rodrigo Balazs
 */
public enum OrderAppValidations {
    PRODUCT_NOT_FOUND(HttpStatus.BAD_REQUEST, "the Product was not found in the Application"),
    CUSTOMER_NOT_FOUND(HttpStatus.BAD_REQUEST, "the Customer was not found in the Application"),
    EMPTY_ORDER_ITEMS(HttpStatus.BAD_REQUEST, "the list of Order´s Items is empty"),
    OUT_OF_STOCK_PRODUCTS(HttpStatus.BAD_REQUEST, "the Order was not placed because some Products are out of stock");

    private final HttpStatus httpStatus;
    private final String message;

    OrderAppValidations(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
