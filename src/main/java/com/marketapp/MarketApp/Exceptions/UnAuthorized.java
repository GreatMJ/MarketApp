package com.marketapp.MarketApp.Exceptions;

public class UnAuthorized extends RuntimeException {
    public UnAuthorized(String message){
        super(message);
    }
}
