package com.rbalazs.stock.service;

import com.rbalazs.stock.enums.StockAppValidations;
import com.rbalazs.stock.exception.StockCustomException;
import com.rbalazs.stock.model.Product;
import com.rbalazs.stock.repository.StockRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Stock Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Checks whether the product given as parameter is in stock or not
     * based on the requested quantity.
     *
     * @param productName the product name to check availability.
     * @param requestedQuantity the product´s requested quantity.
     *
     * @return true if the product is in stock, false otherwise.
     */
    public boolean isInStock(String productName, int requestedQuantity) {
        Product product = stockRepository.findByName(productName);
        if (product == null) {
            throw new StockCustomException(StockAppValidations.ENTITY_NOT_FOUND);
        }
        return product.getAvailableQuantity() > requestedQuantity;
    }
}
