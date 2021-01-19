package com.zeref.SpringReact.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zeref.SpringReact.model.Orders;
import com.zeref.SpringReact.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:8081")

public class OrdersController {
    
    @Autowired
    private OrdersRepository ordersRepository;

    //gets ALL orders made
    @GetMapping(value = "/all")
    public ResponseEntity<List<Orders>> getAll(){
        try {
            List<Orders> orders = new ArrayList<Orders>();
            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //gets order by id if it exists
    @GetMapping(value = "/get")
    public ResponseEntity<Orders> get(@RequestParam("orderId") int orderId ){
        Optional<Orders> orderData = ordersRepository.findById(orderId);

        if (orderData.isPresent()) {
            return new ResponseEntity<>(orderData.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //persist orders
    @PostMapping(value = "/add")
    public ResponseEntity<Orders> pesist(@RequestBody final Orders orders){
       try {
           Orders order = ordersRepository
                .save(new Orders(orders.getOrderid(), orders.getUser(), orders.getCategory()));
            return new ResponseEntity<>(order, HttpStatus.CREATED);  
       } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    //delete order
    @DeleteMapping(value = "/delete")
    public ResponseEntity<HttpStatus> delete(@PathVariable int orderId){
        try {
            ordersRepository.deleteById(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //put an order
    @PutMapping(value = "/put")
    public ResponseEntity<Orders> put(@PathVariable int orderId, @RequestBody Orders orders){
        Optional<Orders> orderData = ordersRepository.findById(orderId);

        if (orderData.isPresent()) {
            Orders ord = orderData.get();
            ord.setOrderid(orders.getOrderid());
            ord.setUser(orders.getUser());
            ord.setCategory(orders.getCategory());

            return new ResponseEntity<>(ordersRepository.save(ord), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
