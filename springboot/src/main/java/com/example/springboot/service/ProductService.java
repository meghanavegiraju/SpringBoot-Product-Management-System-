package com.example.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.model.Product;
import com.example.springboot.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product getById(int id) {
        return repo.findById(id);
    }

    public int create(Product p) {
        return repo.save(p);
    }

    public int update(Product p) {
        return repo.update(p);
    }

    public int delete(int id) {
        return repo.delete(id);
    }
}
