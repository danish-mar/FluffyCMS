package com.keqing.model;

import java.util.Date;

/**
 * The Product class represents a product in an e-commerce system,
 * containing all necessary details such as ID, name, description,
 * category ID, price, stock quantity, weight, image URL, creation
 * date, and availability status.
 */
public class Product {

    private int id;
    private String name;
    private String description;
    private int categoryId;
    private double price;
    private int stockQuantity;
    private double weight;
    private String imageUrl;
    Date createdAt;
    boolean isAvailable;


    public Product(int id, String name, String description, int categoryId, double price, int stockQuantity, double weight, String imageUrl, Date createdAt, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getWeight() {
        return weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public boolean isAvailable() {
        return isAvailable;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "------------------\n" + "✨ Product Details ✨\n" +
                "📦 **ID**: " + id + "\n" +
                "🌟 **Name**: '" + name + "'\n" +
                "📝 **Description**: '" + description + "'\n" +
                "🔖 **Category ID**: " + categoryId + "\n" +
                "💰 **Price**: $" + price + "\n" +
                "📦 **Stock Quantity**: " + stockQuantity + "\n" +
                "⚖️ **Weight**: " + weight + " kg\n" +
                "🖼️ **Image URL**: '" + imageUrl + "'\n" +
                "📅 **Created At**: " + createdAt + "\n" +
                "✅ **Available**: " + (isAvailable ? "Yes" : "No");
    }

}
