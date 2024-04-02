package com.marketapp.MarketApp.Services;

import com.marketapp.MarketApp.Repositories.UserRepository;
import com.marketapp.MarketApp.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // serivce to register user in marketApp
    public void registerUser(AppUser user){
        userRepository.save(user);
    }

    // service to get user with help of id
    public  AppUser getUserById(UUID id){
        return userRepository.findById(id).orElse(null);
    }


}
