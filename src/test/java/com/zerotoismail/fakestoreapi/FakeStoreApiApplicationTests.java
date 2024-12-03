package com.zerotoismail.fakestoreapi;

import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.repositories.categories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FakeStoreApiApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void findCategoryByName(){
        Category category = categoryRepository.findByName("Smart Phone");
        System.out.println("Category: " + category.toString());
    }

    @Test
    void findAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        categoryList.forEach(System.out::println);
    }

    @Test
    void checkIfCategoryExists(){
        boolean exists = categoryRepository.existsCategoryByName("smart phone");
        System.out.println("Category Exists: " + exists);
    }

    @Test
    void checkIfCategoryNotIgnoreCaseExists(){
        boolean exists = categoryRepository.existsCategoryByNameNotIgnoreCase("smart phone");
        System.out.println("Category Exists: " + exists);
    }

    @Test
    void findByNameContaining(){
        List<Category> categoryList = categoryRepository.findByNameContaining("Z");
        categoryList.forEach(System.out::println);
    }

    @Test
    void findCategoryByNameEndingWith(){
        List<Category> categoryList = categoryRepository.findCategoryByNameEndingWith("e");
        categoryList.forEach(System.out::println);
    }


}
