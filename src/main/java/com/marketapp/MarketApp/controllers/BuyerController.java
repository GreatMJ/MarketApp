package com.marketapp.MarketApp.controllers;

import com.marketapp.MarketApp.Services.BuyerService;
import com.marketapp.MarketApp.dto.OrderedProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    @Autowired
    private BuyerService buyerService;

    @PostMapping("/orders/place")
    public String placeOrder(@RequestBody List<OrderedProductDTO> orderedProductsList,@RequestParam UUID buyerId){
        buyerService.placeOrder(orderedProductsList,buyerId);
        return "Congratulations! Your order has been successfully placed. Happy shopping!";
    }
}
