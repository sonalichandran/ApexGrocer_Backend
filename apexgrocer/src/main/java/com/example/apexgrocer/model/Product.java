package com.example.apexgrocer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long productId;
    private String productName;
    private String productcategory;
    private long productcost;
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="order_id",referencedColumnName="orderid")
    @JsonBackReference
    private Orders orders;
    public Product() {
    }
    public Product(long productId, String productName, String productcategory, long productcost) {
        this.productId = productId;
        this.productName = productName;
        this.productcategory = productcategory;
        this.productcost = productcost;
    }
    public long getProductId() {
        return productId;
    }
    public void setProductId(long productId) {
        this.productId = productId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public String getProductcategory() {
        return productcategory;
    }
    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }
    public long getProductcost() {
        return productcost;
    }
    public void setProductcost(long productcost) {
        this.productcost = productcost;
    }
    public Orders getOrders() {
        return orders;
    }
    public void setOrders(Orders orders) {
        this.orders = orders;
    }
    
    


    
}
