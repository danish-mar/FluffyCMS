package com.keqing.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.keqing.Utils.UrlTools.convertToJdbcUrl;

/**
 * This class provides an interface for interacting with the product database.
 * It supports CRUD operations and various queries related to products.
 */
public class ProductRepository {

    private final String databaseUrl;
    private final String username;
    private final String password;

    public ProductRepository(String url, String username, String password) {
        this.databaseUrl = convertToJdbcUrl(url);
        this.username = username;
        this.password = password;
    }

    private Connection connect() throws SQLException {
        try {
            return DriverManager.getConnection(databaseUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
            throw e; // Re-throw to handle it further up the call stack if necessary
        }
    }

    public void save(Product product) {
        String sql = "INSERT INTO products (name, description, category, price, stock_quantity, weight, image_url, is_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getCategoryId());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStockQuantity());
            pstmt.setDouble(6, product.getWeight());
            pstmt.setString(7, product.getImageUrl());
            pstmt.setBoolean(8, product.isAvailable());
            pstmt.executeUpdate();

            System.out.println("Product saved: " + product.getName());
        } catch (SQLException e) {
            System.out.println("Database error while saving product: " + e.getMessage());
        }
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Product product = new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getDouble("weight"),
                        rs.getString("image_url"),
                        rs.getTimestamp("created_at"),
                        rs.getBoolean("is_available")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            System.out.println("Database error while retrieving products: " + e.getMessage());
        }
        return products;
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding product by ID: " + e.getMessage());
        }
        return null; // or throw an exception, depending on your error handling strategy
    }

    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, description = ?, category = ?, price = ?, stock_quantity = ?, weight = ?, image_url = ?, is_available = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, product.getName());
            pstmt.setString(2, product.getDescription());
            pstmt.setInt(3, product.getCategoryId());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setInt(5, product.getStockQuantity());
            pstmt.setDouble(6, product.getWeight());
            pstmt.setString(7, product.getImageUrl());
            pstmt.setBoolean(8, product.isAvailable());
            pstmt.setInt(9, product.getId());
            pstmt.executeUpdate();

            System.out.println("Product updated: " + product.getName());
        } catch (SQLException e) {
            System.out.println("Database error while updating product: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Product deleted with ID: " + id);
        } catch (SQLException e) {
            System.out.println("Database error while deleting product: " + e.getMessage());
        }
    }

    public List<Product> findByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE category = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, categoryId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding products by category: " + e.getMessage());
        }
        return products;
    }

    public List<Product> findByAvailability(boolean isAvailable) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE is_available = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, isAvailable);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding products by availability: " + e.getMessage());
        }
        return products;
    }

    public int countProducts() {
        String sql = "SELECT COUNT(*) FROM products";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Database error while counting products: " + e.getMessage());
        }
        return 0;
    }

    public List<Product> findByPriceRange(double minPrice, double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minPrice);
            pstmt.setDouble(2, maxPrice);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding products by price range: " + e.getMessage());
        }
        return products;
    }

    public List<Product> searchByName(String name) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while searching products by name: " + e.getMessage());
        }
        return products;
    }

    public List<Product> findRecent(int limit) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products ORDER BY created_at DESC LIMIT ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, limit);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding recent products: " + e.getMessage());
        }
        return products;
    }

    public List<Product> findLowStock(int threshold) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE stock_quantity < ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, threshold);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding products with low stock: " + e.getMessage());
        }
        return products;
    }

    public void saveAll(List<Product> products) {
        String sql = "INSERT INTO products (name, description, category, price, stock_quantity, weight, image_url, is_available) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Product product : products) {
                pstmt.setString(1, product.getName());
                pstmt.setString(2, product.getDescription());
                pstmt.setInt(3, product.getCategoryId());
                pstmt.setDouble(4, product.getPrice());
                pstmt.setInt(5, product.getStockQuantity());
                pstmt.setDouble(6, product.getWeight());
                pstmt.setString(7, product.getImageUrl());
                pstmt.setBoolean(8, product.isAvailable());
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            System.out.println("All products saved successfully.");
        } catch (SQLException e) {
            System.out.println("Database error while saving products: " + e.getMessage());
        }
    }

    public void updateStock(int productId, int newStockQuantity) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newStockQuantity);
            pstmt.setInt(2, productId);
            pstmt.executeUpdate();
            System.out.println("Stock quantity updated for product ID: " + productId);
        } catch (SQLException e) {
            System.out.println("Database error while updating stock quantity: " + e.getMessage());
        }
    }

    public Map<Integer, Integer> countProductsByCategory() {
        Map<Integer, Integer> categoryCounts = new HashMap<>();
        String sql = "SELECT category, COUNT(*) AS count FROM products GROUP BY category";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                categoryCounts.put(rs.getInt("category"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            System.out.println("Database error while counting products by category: " + e.getMessage());
        }
        return categoryCounts;
    }

    public List<Product> findByWeightRange(double minWeight, double maxWeight) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE weight BETWEEN ? AND ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minWeight);
            pstmt.setDouble(2, maxWeight);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Product product = new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("category"),
                            rs.getDouble("price"),
                            rs.getInt("stock_quantity"),
                            rs.getDouble("weight"),
                            rs.getString("image_url"),
                            rs.getTimestamp("created_at"),
                            rs.getBoolean("is_available")
                    );
                    products.add(product);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error while finding products by weight range: " + e.getMessage());
        }
        return products;
    }


    public ProductDetail getProductDetails(int productId) {
        ProductDetail productDetail = null;
        String sqlProduct = "SELECT p.*, pc.name AS category_name FROM products p " +
                "JOIN product_category pc ON p.category = pc.id WHERE p.id = ?";
        String sqlReviews = "SELECT * FROM review WHERE product = ?";

        try (Connection conn = connect();
             PreparedStatement pstmtProduct = conn.prepareStatement(sqlProduct);
             PreparedStatement pstmtReviews = conn.prepareStatement(sqlReviews)) {

            // Get product details
            pstmtProduct.setInt(1, productId);
            try (ResultSet rsProduct = pstmtProduct.executeQuery()) {
                if (rsProduct.next()) {
                    Product product = new Product(
                            rsProduct.getInt("id"),
                            rsProduct.getString("name"),
                            rsProduct.getString("description"),
                            rsProduct.getInt("category"),
                            rsProduct.getDouble("price"),
                            rsProduct.getInt("stock_quantity"),
                            rsProduct.getDouble("weight"),
                            rsProduct.getString("image_url"),
                            rsProduct.getTimestamp("created_at"),
                            rsProduct.getBoolean("is_available")
                    );
                    String categoryName = rsProduct.getString("category_name");
                    productDetail = new ProductDetail(product, categoryName);
                }
            }

            // Get reviews
            pstmtReviews.setInt(1, productId);
            try (ResultSet rsReviews = pstmtReviews.executeQuery()) {
                List<Review> reviews = new ArrayList<>();
                while (rsReviews.next()) {
                    Review review = new Review(
                            rsReviews.getInt("revId"),
                            rsReviews.getInt("product"),
                            rsReviews.getInt("star"),
                            rsReviews.getString("description"),
                            rsReviews.getTimestamp("created_at")
                    );
                    reviews.add(review);
                }
                if (productDetail != null) {
                    productDetail.setReviews(reviews);
                }
            }

        } catch (SQLException e) {
            System.out.println("Database error while fetching product details: " + e.getMessage());
        }
        return productDetail;
    }

    public void addReview(int productId, int star, String description) {
        String sql = "INSERT INTO review (product, star, description) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.setInt(2, star);
            pstmt.setString(3, description);
            pstmt.executeUpdate();

            System.out.println("Review added for product ID: " + productId);

        } catch (SQLException e) {
            System.out.println("Database error while adding review: " + e.getMessage());
        }
    }


    public void addProductToFeatured(int productId, Timestamp fromDate, Timestamp toDate) {
        String sql = "INSERT INTO featured_product (id, `from`, `to`) VALUES (?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.setTimestamp(2, fromDate);
            pstmt.setTimestamp(3, toDate);
            pstmt.executeUpdate();

            System.out.println("Product ID " + productId + " added to featured list from " + fromDate + " to " + toDate);

        } catch (SQLException e) {
            System.out.println("Database error while adding product to featured list: " + e.getMessage());
        }
    }

    // Overloaded method for Product object
    public void addProductToFeatured(Product product, Timestamp fromDate, Timestamp toDate) {
        addProductToFeatured(product.getId(), fromDate, toDate);
    }

    public void removeProductFromFeatured(int productId) {
        String sql = "DELETE FROM featured_product WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, productId);
            pstmt.executeUpdate();

            System.out.println("Product ID " + productId + " removed from featured list");

        } catch (SQLException e) {
            System.out.println("Database error while removing product from featured list: " + e.getMessage());
        }
    }

    public void removeProductFromFeatured(Product product) {
        removeProductFromFeatured(product.getId());
    }

    public void createCategory(String name, String description) {
        String sql = "INSERT INTO product_category (name, description) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.executeUpdate();

            System.out.println("Category created: " + name);

        } catch (SQLException e) {
            System.out.println("Database error while creating category: " + e.getMessage());
        }
    }

    public void deleteCategory(int categoryId) {
        String sql = "DELETE FROM product_category WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, categoryId);
            pstmt.executeUpdate();

            System.out.println("Category ID " + categoryId + " deleted");

        } catch (SQLException e) {
            System.out.println("Database error while deleting category: " + e.getMessage());
        }
    }
}
