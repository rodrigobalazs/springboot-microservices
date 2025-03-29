package com.rbalazs.stock.exception;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Used to intercept {@link StockCustomException}
 *
 * @author Rodrigo Balazs
 */
@ControllerAdvice
public class StockCustomExceptionHandler {

    /**
     * Intercepts a given {@link StockCustomException} in order to return to the view an HTTP RESPONSE with the exception
     * message.
     */
    @ExceptionHandler(StockCustomException.class)
    public ResponseEntity<Object> handleStockCustomException(final StockCustomException ex) {
        return ResponseEntity.status(ex.getStatus()).body(ex.getMessage());
    }
}