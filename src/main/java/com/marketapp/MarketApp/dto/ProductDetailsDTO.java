package com.marketapp.MarketApp.dto;


import lombok.*;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductDetailsDTO {

    private UUID id;
    private String name;

    private String category;
    private int quantity;
    private int price;

    private String sellerName;


    private Double rating;
    private String description;

}
