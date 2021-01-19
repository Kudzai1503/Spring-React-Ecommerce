package com.zeref.SpringReact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zeref.SpringReact.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
