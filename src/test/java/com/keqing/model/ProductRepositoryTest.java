package com.keqing.model;

import org.junit.jupiter.api.*;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    void init() {
        //assuming this is the valid database
        productRepository = new ProductRepository("jdbc:h2:mem:testdb", "sa", "password");
    }

    @Test
    void testGetProductDetailsWithInvalidProductId() {

        //when you try to get product details with invalid product id, it should return null
        ProductDetail result = productRepository.getProductDetails(-1);
        assertNull(result, "Product Detail must be null for invalid product id");
    }

    //You can add more similar test cases

    @AfterEach
    void cleanup() {
        //Here you should cleanup the DB to its previous state. 
    }
}