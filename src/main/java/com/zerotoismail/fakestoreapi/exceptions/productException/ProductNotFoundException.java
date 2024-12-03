package com.zerotoismail.fakestoreapi.exceptions.productException;


public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message) {
        super(message);
    }
}
