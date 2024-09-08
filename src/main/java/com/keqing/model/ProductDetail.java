package com.keqing.model;

import java.util.ArrayList;
import java.util.List;

public class ProductDetail {
    private Product product;
    private String categoryName;
    private List<Review> reviews;

    public ProductDetail(Product product, String categoryName) {
        this.product = product;
        this.categoryName = categoryName;
        this.reviews = new ArrayList<>();
    }

    // Getters and setters
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
