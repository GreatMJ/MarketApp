package com.marketapp.MarketApp.Services;

import com.marketapp.MarketApp.Exceptions.ResourceNotFound;
import com.marketapp.MarketApp.Exceptions.UnAuthorized;
import com.marketapp.MarketApp.Repositories.ProductRepository;

import com.marketapp.MarketApp.models.AppUser;
import com.marketapp.MarketApp.models.Product;
import com.marketapp.MarketApp.dto.ProductDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  UserService userService;

    // register product in market (on app)
    public void registerProduct(Product product, UUID id){
          // first we have to check weather user with this id present in database or not
        AppUser user=userService.getUserById(id);
        if(user==null){
            throw new ResourceNotFound(String.format("Seller with sellerId %s does not exist in system.", id.toString()));
        }
        if(!user.getUserType().equals("SELLER")){
            throw new UnAuthorized(String.format("User with id %s does not have access to perform this operation",id.toString()));
        }

        // set the seller of product
        product.setSeller(user);


        productRepository.save(product);

    }

    // get product by Id
    public Product getProductById(UUID prductId){
        return productRepository.findById(prductId).orElse(null);
    }


    // methods to get products either by category or productName
    // by both category and productName
    public List<ProductDetailsDTO> getProductsByCategoryAndProductName(String category,String productName){
        List<Product> productList=productRepository.getProductsByCategoryAndProductName(category,productName);
        List<ProductDetailsDTO> productDTOList=convertToProductDetailsDTO(productList);
        return productDTOList;
    }

    // by category
    public List<ProductDetailsDTO> getProductsByCategory(String category){
        List<Product> productList=productRepository.getProductsByCategory(category);
        List<ProductDetailsDTO> productDTOList=convertToProductDetailsDTO(productList);
        return productDTOList;
    }

    // by productName
    public List<ProductDetailsDTO> getProductsByProductName(String productName){
        List<Product> productList=productRepository.getProductsByProductName(productName);
        List<ProductDetailsDTO> productDTOList=convertToProductDetailsDTO(productList);
        return productDTOList;
    }
    // if category and product name not specified
    public List<ProductDetailsDTO> getAllProducts(){

        List<Product> productList=productRepository.findAll();
        List<ProductDetailsDTO> productDTOList=convertToProductDetailsDTO(productList);
        return productDTOList;
    }


    // convert prdouct details getting from db to dto

    private List<ProductDetailsDTO> convertToProductDetailsDTO(List<Product> productList){
        List<ProductDetailsDTO> list=new ArrayList<>();

        for(Product product:productList){
            ProductDetailsDTO productDTO=new ProductDetailsDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategory(product.getCategory());
            productDTO.setPrice(product.getPrice());
            productDTO.setRating(productDTO.getRating());
            productDTO.setQuantity(productDTO.getQuantity());
            productDTO.setDescription(productDTO.getDescription());
            productDTO.setSellerName(product.getSeller().getName());
            list.add(productDTO);

        }
        return list;
    }



}
