package com.rbalazs.stock.repository;

import com.rbalazs.stock.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
