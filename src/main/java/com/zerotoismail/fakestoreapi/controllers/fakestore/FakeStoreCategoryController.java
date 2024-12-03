package com.zerotoismail.fakestoreapi.controllers.fakestore;

import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.ICategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fakeStore")
public class FakeStoreCategoryController {

    private ICategoryService categoryService;
    public FakeStoreCategoryController(@Qualifier("selfCategoryServiceImpl") ICategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getSpecificCategoryProducts(@PathVariable String category){
        List<Product> products = categoryService.findProductByCategory(category);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
