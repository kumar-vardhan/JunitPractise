package com.JunitPractise.repo;

import com.JunitPractise.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products,Integer> {

}
