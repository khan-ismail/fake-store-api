package com.zerotoismail.fakestoreapi.services;

import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    List<Product> findProductByCategory(String category);
    Category save(long id, String categoryName);
}
