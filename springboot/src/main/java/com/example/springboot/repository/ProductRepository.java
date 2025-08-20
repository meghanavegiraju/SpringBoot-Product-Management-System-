package com.example.springboot.repository;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.springboot.model.Product;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // RowMapper to convert DB row into Product object
    private RowMapper<Product> rowMapper = (ResultSet rs, int rowNum) -> {
        Product p = new Product();
        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getDouble("price"));
        p.setDescription(rs.getString("description"));
        return p;
    };

    // Get all products
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", rowMapper);
    }

    // Get product by ID
    public Product findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id = ?", rowMapper, id);
    }

    // Add a new product
    public int save(Product p) {
        return jdbcTemplate.update(
            "INSERT INTO product(name, price, description) VALUES (?, ?, ?)",
            p.getName(), p.getPrice(), p.getDescription()
        );
    }

    // Update existing product
    public int update(Product p) {
        return jdbcTemplate.update(
            "UPDATE product SET name=?, price=?, description=? WHERE id=?",
            p.getName(), p.getPrice(), p.getDescription(), p.getId()
        );
    }

    // Delete a product
    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM product WHERE id=?", id);
    }
}