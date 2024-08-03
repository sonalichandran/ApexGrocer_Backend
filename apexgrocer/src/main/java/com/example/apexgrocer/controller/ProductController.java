package com.example.apexgrocer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apexgrocer.model.Product;
import com.example.apexgrocer.repository.ProductRepository;
import com.example.apexgrocer.service.ProductService;


import java.util.*;

@RestController
@RequestMapping("/product")
@CrossOrigin("http://localhost:5173/")
public class ProductController {
    @Autowired
    ProductRepository pr;
    @Autowired
    ProductService ps;

    @PostMapping("/register")
    public Product post(@RequestBody Product p) {
        return pr.save(p);
    }

    @GetMapping("/getall")
    public List<Product> getAll() {
        return pr.findAll();
    }
    @GetMapping("/getbyid/{Id}")
    public Optional<Product> getbyid(@PathVariable Long Id)
    {
        return pr.findById(Id);
    }
    @DeleteMapping("/deletebyid/{Id}")
    public String deletebyId(@PathVariable Long Id)
    {
        Optional<Product> p=pr.findById(Id);
        if(p!=null)
        {
            return "Product deleted successful";
        }
        return "Product is not available of product Id given";
    }
}
