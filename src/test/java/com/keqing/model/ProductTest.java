package com.keqing.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testGetPrice_ForPositivePrice() {
        double expectedPrice = 20.5;
        Product product = new Product(1, "Test Product", "This is a test product", 100, expectedPrice, 10, 2.0, "http://test.com/img.jpg", new Date(), true);
        double actualPrice = product.getPrice();
        assertEquals(expectedPrice, actualPrice, "The expected and actual price do not match for positive price");
    }

    @Test
    void testGetPrice_ForZeroPrice() {
        double expectedPrice = 0.0;
        Product product = new Product(1, "Test Product", "This is a test product", 100, expectedPrice, 10, 2.0, "http://test.com/img.jpg", new Date(), true);
        double actualPrice = product.getPrice();
        assertEquals(expectedPrice, actualPrice, "The expected and actual price do not match for zero price");
    }

    @Test
    void testGetPrice_ForNegativePrice() {
        double expectedPrice = -10.0;
        Product product = new Product(1, "Test Product", "This is a test product", 100, expectedPrice, 10, 2.0, "http://test.com/img.jpg", new Date(), true);
        double actualPrice = product.getPrice();
        assertEquals(expectedPrice, actualPrice, "The expected and actual price do not match for negative price");
    }
}