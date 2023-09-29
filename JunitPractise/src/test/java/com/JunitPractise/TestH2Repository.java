package com.JunitPractise;

import com.JunitPractise.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Products,Integer> {
}
