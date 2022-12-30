package com.ecommerce.exception;

public class ProductNotFoundException extends IllegalArgumentException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
