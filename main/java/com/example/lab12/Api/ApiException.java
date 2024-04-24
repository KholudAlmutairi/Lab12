package com.example.lab12.Api;

public class ApiException extends RuntimeException{
    public ApiException (String massage){
        super(massage);
    }
}
