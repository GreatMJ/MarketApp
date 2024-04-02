package com.marketapp.MarketApp.Repositories;

import com.marketapp.MarketApp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "select * from product where category=:category and name=:productName",nativeQuery = true)
    public List<Product> getProductsByCategoryAndProductName(String category,String productName);
    @Query(value = "select * from product where category=:category",nativeQuery = true)
    public List<Product> getProductsByCategory(String category);
    @Query(value = "select * from product where name=:productName",nativeQuery = true)
    public List<Product> getProductsByProductName(String productName);
}
