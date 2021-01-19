package com.zeref.SpringReact.repository;

import com.zeref.SpringReact.model.Orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    
}
