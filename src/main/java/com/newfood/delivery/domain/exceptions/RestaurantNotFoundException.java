package com.newfood.delivery.domain.exceptions;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException( String message){
        super(message);
    }
}
