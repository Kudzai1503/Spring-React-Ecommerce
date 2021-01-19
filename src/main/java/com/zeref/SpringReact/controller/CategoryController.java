package com.zeref.SpringReact.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zeref.SpringReact.model.Category;
import com.zeref.SpringReact.repository.CategoryRepository;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:8081")

public class CategoryController{
    @Autowired
    private CategoryRepository categoryRepository;

    //gets all categories and returns categories
    @GetMapping(value = "/all")
    public ResponseEntity<List<Category>> getAll(String categoryName){
        try {
            List<Category> category = new ArrayList<Category>();
            if(categoryName == null){
                categoryRepository.findAll();
            }
            if (category.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            
            return new ResponseEntity<>(category, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //gets category if category exist
    @GetMapping(value = "/get")
    public ResponseEntity<Category> get(@RequestParam("categoryId") int categoryId){
        Optional<Category> categoryData = categoryRepository.findById(categoryId);

        if (categoryData.isPresent()){
            return new ResponseEntity<>(categoryData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    //persist category
    @PostMapping(value = "/add")
    public ResponseEntity<Category> persist(@RequestBody final Category category){
        try {
            Category categ = categoryRepository
               .save(new Category(category.getName(), category.getDescription(), 0, null, null));
            return new ResponseEntity<>(categ, HttpStatus.CREATED);

          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }

    //delete category
    @DeleteMapping(value = "/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable int categoryId){
        try {
            categoryRepository.deleteById(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //put category
    @PutMapping(value = "/put{categoryId}")
    public ResponseEntity<Category> put(@PathVariable int categoryId, @RequestBody Category category){
        Optional<Category> categorydata = categoryRepository.findById(categoryId);

        if (categorydata.isPresent()) {
            Category categ = categorydata.get();
            categ.setName(category.getName());
            categ.setDescription(category.getDescription());
            categ.setPicture(category.getPicture());

            return new ResponseEntity<>(categoryRepository.save(categ), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }


}
