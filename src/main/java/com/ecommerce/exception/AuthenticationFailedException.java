package com.ecommerce.exception;

public class AuthenticationFailedException extends IllegalArgumentException{

    public AuthenticationFailedException(String msg) {
        super(msg);
    }
}
