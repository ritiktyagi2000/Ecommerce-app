package com.ecommerce.exception;

public class CustomException extends IllegalArgumentException{
    public CustomException(String s) {
        super(s);
    }
}
