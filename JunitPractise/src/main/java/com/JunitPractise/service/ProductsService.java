package com.JunitPractise.service;

import com.JunitPractise.entity.Products;
import com.JunitPractise.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepo productsRepo;

    public Products addProducts(Products products) {
       return productsRepo.save(products);
        //return "Product added successfully";
    }

    public List<Products> allProducts() {
        return productsRepo.findAll();
    }

    public Optional<Products> findProductById(int id) {
        return productsRepo.findById(id);
    }

    public Products updateProducts(Products products, int id) {
        Optional<Products> products1 = productsRepo.findById(id);
        Products products2 = products1.get();
        products2.setName(products.getName());
        products2.setPrice(products.getPrice());
        return productsRepo.save(products2);
    }

    public String deleteProduct(int id) {
        productsRepo.deleteById(id);
        return "Product deleted sucessfully";
    }
}
