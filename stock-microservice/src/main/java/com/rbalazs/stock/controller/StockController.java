package com.rbalazs.stock.controller;

import com.rbalazs.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Stock REST Controller.
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/stock")
public class StockController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/isInStock")
    public boolean isInStock(@RequestParam String productName, @RequestParam int requestedQuantity) {
        LOGGER.info("starts to execute stockController.isInStock() with product name:{}", productName);
        return stockService.isInStock(productName, requestedQuantity);
    }
}

