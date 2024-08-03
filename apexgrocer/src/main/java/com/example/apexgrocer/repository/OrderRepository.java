package com.example.apexgrocer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apexgrocer.model.Orders;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    
}
