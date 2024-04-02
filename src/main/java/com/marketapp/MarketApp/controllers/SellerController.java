package com.marketapp.MarketApp.controllers;

import com.marketapp.MarketApp.Exceptions.ResourceNotFound;
import com.marketapp.MarketApp.Exceptions.UnAuthorized;
import com.marketapp.MarketApp.Services.ProductService;
import com.marketapp.MarketApp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private ProductService productService;
    @PostMapping("/product/add")
    public String registerProduct(@RequestBody Product product, @RequestParam UUID id){
        try{
            productService.registerProduct(product,id);
        }catch (ResourceNotFound resourceNotFound){
          return  resourceNotFound.getMessage();
        }catch (UnAuthorized unAuthorized){
           return unAuthorized.getMessage();
        }

        return "Product has been saved Successfully in the system.";
    }
}
