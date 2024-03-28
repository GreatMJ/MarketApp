package com.marketapp.MarketApp.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int totalPrice;
    private  int totalQuantity;

    @ManyToOne
    private AppUser user;

    @OneToMany
    private List<Product> products;

    private  String status;
    private String paymentMode;

    public Orders(UUID id, int totalPrice, int totalQuantity, AppUser user, List<Product> products, String status, String paymentMode) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
        this.user = user;
        this.products = products;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public Orders(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
