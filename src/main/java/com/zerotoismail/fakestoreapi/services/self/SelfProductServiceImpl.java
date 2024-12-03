package com.zerotoismail.fakestoreapi.services.self;

import com.zerotoismail.fakestoreapi.exceptions.categoryException.CategoryNotFoundException;
import com.zerotoismail.fakestoreapi.exceptions.productException.ProductNotFoundException;
import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.repositories.categories.CategoryRepository;
import com.zerotoismail.fakestoreapi.repositories.products.ProductRepository;
import com.zerotoismail.fakestoreapi.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SelfProductServiceImpl implements IProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;


    @Override
    public Product findProduct(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product " + id + " not found");
        }
        return product.get();
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(String title, String description, String image, String category, double price) {

        Category dbCategory = categoryRepository.findByName(category);

        if(dbCategory == null){
            dbCategory = new Category(category);
        }

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        product.setCategory(dbCategory);

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long id, String title, String description, String image, String category, double price) {

        Optional<Product> dbProduct = productRepository.findById(id);
        if(dbProduct.isEmpty()){
            throw new ProductNotFoundException("Product " + id + " not found");
        }

        Category dbCategory = categoryRepository.findByName(category);

        if(dbCategory == null){
            throw new CategoryNotFoundException("Category " + category + " not found");
        }

        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);
        product.setCategory(dbCategory);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long id) {
        Optional<Product> dbProduct = productRepository.findById(id);
        if(dbProduct.isEmpty()){
            throw new ProductNotFoundException("Product " + id + " not found");
        }
        productRepository.deleteById(id);
        return dbProduct.get();
    }
}
