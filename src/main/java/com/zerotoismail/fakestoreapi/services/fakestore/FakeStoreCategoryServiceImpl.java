package com.zerotoismail.fakestoreapi.services.fakestore;

import com.zerotoismail.fakestoreapi.dto.ProductDto;
import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class FakeStoreCategoryServiceImpl implements ICategoryService {


    private RestTemplate restTemplate;

    @Override
    public List<Category> findAll() {
        ResponseEntity<String[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        String[] categories = response.getBody();

        return Arrays.stream(categories).map(Category::new).toList();
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/category/" + category,
                ProductDto[].class
        );

        ProductDto[] productDtos = response.getBody();
        return Arrays.stream(productDtos).map((product) -> product.toProduct()).toList();
    }

    @Override
    public Category save(long id, String categoryName) {
        return null;
    }
}
