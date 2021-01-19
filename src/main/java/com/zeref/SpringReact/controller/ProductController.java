package com.zeref.SpringReact.controller;

import com.zeref.SpringReact.model.Product;
import com.zeref.SpringReact.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:8081")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
     
    //gets all products
    @GetMapping(value = "/all")
    public ResponseEntity<List<Product>> getAll(String productName){
        try {
            List<Product> product = new ArrayList<Product>();
            if(productName == null){
                productRepository.findAll();
            }
            if(product.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //gets a specific product based on id if it exists
    @GetMapping(value = "/get")
    public ResponseEntity<Product> get(@RequestParam("id") int id){
        Optional<Product> prodData = productRepository.findById(id);

        if (prodData.isPresent()) {
            return new ResponseEntity<>(prodData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //persist product
    @PostMapping(value = "/add")
    public ResponseEntity<Product> persist(@RequestBody final Product product){
        try {
            Product prod = productRepository
                .save(new Product(product.getId(), product.getproductName(), product.getProductDesc(),
                 product.getWeight(), product.getPrice(), product.getPic1(), product.getPic2(),
                product.getPic3(), product.getCategory()));

            return new ResponseEntity<>(prod, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //delete a product
    @DeleteMapping(value = "/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable int id){
        try {
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //put a product
    @PutMapping(value = "/put/{id}")
    public ResponseEntity<Product> put(@PathVariable int id, @RequestBody Product product){
        Optional<Product> prodData = productRepository.findById(id);

        if (prodData.isPresent()) {
            Product prod = prodData.get();
            prod.setProductName(product.getproductName());
            prod.setProductDesc(product.getProductDesc());
            prod.setWeight(product.getWeight());
            prod.setPrice(product.getPrice());
            prod.setPic1(product.getPic1());
            prod.setPic2(product.getPic2());
            prod.setPic3(product.getPic3());
            prod.setCategory(product.getCategory());

            return new ResponseEntity<>(productRepository.save(prod), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
