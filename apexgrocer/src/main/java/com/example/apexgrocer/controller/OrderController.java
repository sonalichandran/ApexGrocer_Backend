package com.example.apexgrocer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apexgrocer.model.Orders;
import com.example.apexgrocer.repository.OrderRepository;
import com.example.apexgrocer.service.OrderService;


import java.util.*;

@RestController
@RequestMapping("/order")
@CrossOrigin("http://localhost:5173/")
public class OrderController {
    @Autowired
    OrderService os;
    @Autowired
    OrderRepository or;
    @PostMapping("/register")
    public Orders post(@RequestBody Orders order)
    {
        return or.save(order);
        
    }
    @GetMapping("/getall")
    public List<Orders> getAll()
    {
        return or.findAll();
    }
    
    

    
}
