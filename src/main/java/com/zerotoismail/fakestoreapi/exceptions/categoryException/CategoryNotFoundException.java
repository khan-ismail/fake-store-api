package com.zerotoismail.fakestoreapi.exceptions.categoryException;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
