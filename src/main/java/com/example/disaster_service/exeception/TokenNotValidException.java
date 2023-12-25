package com.example.disaster_service.exeception;

public class TokenNotValidException extends RuntimeException{

    public TokenNotValidException(){
        super("token not valid");
    }
}
