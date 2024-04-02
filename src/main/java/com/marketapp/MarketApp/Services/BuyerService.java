package com.marketapp.MarketApp.Services;

import com.marketapp.MarketApp.Repositories.OrderRepository;
import com.marketapp.MarketApp.dto.OrderedProductDTO;
import com.marketapp.MarketApp.models.AppUser;

import com.marketapp.MarketApp.models.Orders;
import com.marketapp.MarketApp.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BuyerService {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    // place order
    public void placeOrder(List<OrderedProductDTO> orderedProducts, UUID buyerId){
        // for now to keep things simple we will not checking userID, we will consider that it will be buyerId for sure
        // in future you can add check and throw error for incorrect id

        // first get the user
        AppUser user=userService.getUserById(buyerId);

        // store the products in list
        List<Product> orderedProductsList=new ArrayList<>();
        int totalPrice=0;
        int totalQuantity=0;

        // now loop over the orderedProducts to calculate price and quantities and convert It to product object
        for(OrderedProductDTO orderedProduct:orderedProducts){
            Product product=productService.getProductById(orderedProduct.getId());
            int quantatiesOrdered=orderedProduct.getQuantity();
            totalQuantity+=quantatiesOrdered;
            totalPrice+=(quantatiesOrdered*product.getPrice());
            orderedProductsList.add(product);
        }

         // create order object
        Orders order=new Orders();
        order.setTotalPrice(totalPrice);
        order.setTotalQuantity(totalQuantity);
        order.setPaymentMode("COD");
        order.setStatus("Pending");
        order.setProducts(orderedProductsList);
        order.setUser(user);

        orderRepository.save(order);


    }
}
