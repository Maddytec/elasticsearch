package com.maddytec.elasticsearch.service;

import com.maddytec.elasticsearch.model.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product create(Product product);

    List<Product> getAllProducts() throws IOException;

    Product getProductById(String id) throws IOException;

    void updateProduct(String id, Product product) throws IOException;

    void deleteProduct(String id) throws IOException;

    List<Product> getProductByCategory(String category) throws IOException;

    List<Product> getProductByRangerPrice(BigDecimal minPrice, BigDecimal maxPrice) throws IOException;
}
