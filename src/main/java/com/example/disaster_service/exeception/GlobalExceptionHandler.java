package com.example.disaster_service.exeception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TokenNotValidException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handelTokenNotValidException(TokenNotValidException e){
        return e.getMessage();
    }

    @ExceptionHandler(DeclarationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelDeclarationException(DeclarationException e){
        return e.getMessage();
    };

    @ExceptionHandler(DisasterNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handelDisasterNotFoundException(DisasterNotFoundException e){
        return e.getMessage();
    }

}
