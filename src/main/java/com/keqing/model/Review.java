package com.keqing.model;

import java.sql.Timestamp;

public class Review {
    private int revId;
    private int product;
    private int star;
    private String description;
    private Timestamp createdAt;

    public Review(int revId, int product, int star, String description, Timestamp createdAt) {
        this.revId = revId;
        this.product = product;
        this.star = star;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters and setters
}
