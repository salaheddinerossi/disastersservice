package com.example.disaster_service.exeception;

public class DisasterNotFoundException extends RuntimeException{

    public DisasterNotFoundException(){
        super("disaster not found");
    }
}
