package com.zeref.SpringReact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeref.SpringReact.model.Category;




public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
