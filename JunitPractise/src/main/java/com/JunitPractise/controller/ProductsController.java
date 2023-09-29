package com.JunitPractise.controller;

import com.JunitPractise.entity.Products;
import com.JunitPractise.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    @PostMapping("/product")
    public Products addProducts(@RequestBody Products products){
        return productsService.addProducts(products);
    }

    @GetMapping("/all")
    public List<Products> allProducts(){
        return productsService.allProducts();
    }

    @GetMapping("/{id}")
    public Optional<Products> getProductsById(@PathVariable("id")int id){
        return productsService.findProductById(id);
    }

    @PutMapping("/update/{id}")
    public Products updateProducts(@RequestBody Products products,@PathVariable("id")int id){
        return productsService.updateProducts(products,id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")int id){
        return productsService.deleteProduct(id);
    }


}
