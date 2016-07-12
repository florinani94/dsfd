package com.evozon.dao;

import com.evozon.domain.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    void addDefaultProducts();

    void addProduct(Product product);
}
