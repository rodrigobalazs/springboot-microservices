package com.rbalazs.stock.repository;

import com.rbalazs.stock.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

/**
 * Stock Repository.
 * Note => to simplify the example the Repository layer is implemented in-memory.
 */
@Repository
public class StockRepository {

    static List<Product> products = new ArrayList<Product>();
    static {
        Product product1 = new Product("productA", 10);
        Product product2 = new Product("productB", 20);
        products.add(product1);
        products.add(product2);
    }

    public Product getProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}
