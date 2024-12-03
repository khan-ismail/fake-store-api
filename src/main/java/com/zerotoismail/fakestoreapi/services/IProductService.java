package com.zerotoismail.fakestoreapi.services;

import com.zerotoismail.fakestoreapi.models.Product;

import java.util.List;

public interface IProductService {
    Product findProduct(long id);
    List<Product> findAllProducts();
    Product saveProduct(String title, String description, String image, String category, double price);
    Product updateProduct(long id, String title, String description, String image, String category, double price);
    Product deleteProduct(long id);
}
