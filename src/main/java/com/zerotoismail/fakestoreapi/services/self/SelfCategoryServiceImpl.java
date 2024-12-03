package com.zerotoismail.fakestoreapi.services.self;

import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.repositories.categories.CategoryRepository;
import com.zerotoismail.fakestoreapi.repositories.products.ProductRepository;
import com.zerotoismail.fakestoreapi.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SelfCategoryServiceImpl implements ICategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> findProductByCategory(String category) {

        List<Product> products = new ArrayList<>();
        products = productRepository.findProductsByCategoryName(category);
        return products;
    }

    @Override
    public Category save(long id, String categoryName) {
        Category category = new Category(categoryName);
        if(id > 0) {
            category.setId(id);
        }
        return categoryRepository.save(category);
    }


}
