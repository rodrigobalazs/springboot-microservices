package com.rbalazs.stock.service;

import com.rbalazs.stock.enums.StockAppValidations;
import com.rbalazs.stock.exception.StockCustomException;
import com.rbalazs.stock.model.Product;
import com.rbalazs.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Stock Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Retrieves a list with all the Products
     *
     * @return a list of {@link Product}
     */
    public List<Product> getProducts() {
        return stockRepository.findAll();
    }

    /**
     * Retrieves a Product by the Product Name given as parameter.
     *
     * @param name the product name to retrieve
     * @return a {@link Product}
     */
    public Product getProductByName(final String name) {
        return stockRepository.findByName(name).orElse(null);
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
    public boolean isInStock(final String productName, final int requestedQuantity) {
        Product product = getProductByName(productName);
        if (product == null) {
            throw new StockCustomException(StockAppValidations.PRODUCT_NOT_FOUND);
        }
        return product.getAvailableQuantity() >= requestedQuantity;
    }

    public void decreaceProductAvailableQuantity(final String productName, final int quantityToDecreace) {
        Product product = getProductByName(productName);
        if (product == null) {
            throw new StockCustomException(StockAppValidations.PRODUCT_NOT_FOUND);
        }
        int updatedQuantity = product.getAvailableQuantity() - quantityToDecreace;
        product.setAvailableQuantity(updatedQuantity);
        stockRepository.save(product);
    }
}
