package com.marketapp.MarketApp.controllers;

import com.marketapp.MarketApp.Services.ProductService;
import com.marketapp.MarketApp.Services.UserService;
import com.marketapp.MarketApp.dto.ProductDetailsDTO;
import com.marketapp.MarketApp.models.AppUser;
import com.marketapp.MarketApp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    // endpoint for registering user in database
    @PostMapping("/user/register")
    public String registerUser(@RequestBody AppUser user){
        userService.registerUser(user);
        return "User got saved Successfully!";
    }

    @GetMapping("/product/search")
    public List<ProductDetailsDTO> searchProduct(@RequestParam(required = false) String category, @RequestParam(required = false) String productName){
        if(category!=null&&productName!=null){
            return productService.getProductsByCategoryAndProductName(category,productName);
        }else if(category!=null){
            return productService.getProductsByCategory(category);
        }else if(productName!=null){
            return productService.getProductsByProductName(productName);
        }else{
            return productService.getAllProducts();
        }

    }
}
