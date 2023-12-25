package com.example.disaster_service.exeception;

public class DeclarationException extends RuntimeException{
    public DeclarationException(){
        super("this disaster Declaration is not found");
    }
}
