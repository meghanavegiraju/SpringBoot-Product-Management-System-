package com.example.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.Product;
import com.example.springboot.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    // GET a product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getById(id);
    }

    // POST a new product
    @PostMapping
    public String createProduct(@RequestBody Product product) {
        service.create(product);
        return "✅ Product added successfully!";
    }

    // PUT to update a product by ID
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product product) {
        product.setId(id); // ensure correct ID is set
        service.update(product);
        return "✅ Product with ID " + id + " updated successfully!";
    }

    // DELETE a product by ID
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        service.delete(id);
        return "🗑️ Product with ID " + id + " deleted successfully!";
    }
}
