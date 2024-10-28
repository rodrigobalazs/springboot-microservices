package com.rbalazs.orders.enums;

import org.springframework.http.HttpStatus;

/**
 * Enum which contains some validation messages related to the Orders app/microservice.
 *
 * @author Rodrigo Balazs
 */
public enum OrderAppValidations {
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "the entity was not found in the Orders application"),
    CUSTOMER_NOT_FOUND(HttpStatus.BAD_REQUEST, "the customer was not found in the Orders application"),
    EMPTY_ORDER_ITEMS(HttpStatus.BAD_REQUEST, "the list of order´s items is empty");

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
