package com.maddytec.elasticsearch.controller;

import com.maddytec.elasticsearch.model.Product;
import com.maddytec.elasticsearch.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProduct() throws IOException {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable("id") String id) throws IOException {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") String id, @RequestBody Product product) throws IOException {
        productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("id") String id) throws IOException {
        productService.deleteProduct(id);
    }

    @GetMapping("/search/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category) throws IOException {
        return productService.getProductByCategory(category);
    }

    @GetMapping("/search/price")
    public List<Product> getProductByRangerPrice(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) throws IOException {
        return productService.getProductByRangerPrice(minPrice, maxPrice);
    }

}
