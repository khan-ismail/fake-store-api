package com.zerotoismail.fakestoreapi.services.fakestore;


import com.zerotoismail.fakestoreapi.dto.ProductDto;
import com.zerotoismail.fakestoreapi.exceptions.productException.ProductNotFoundException;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class FakeStoreProductServiceImpl implements IProductService {

    private RestTemplate restTemplate;

    @Override
    public Product findProduct(long id) {
        ResponseEntity<ProductDto> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + id,
                ProductDto.class
        );

        if(response.getStatusCode() != HttpStatus.OK){
            throw new ProductNotFoundException("Product not found for id: " + id);
        }

        return response.getBody().toProduct();
    }

    @Override
    public List<Product> findAllProducts() {

        ResponseEntity<ProductDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products",
                ProductDto[].class
        );

        ProductDto[] productDtoList = response.getBody();

        return Arrays
                .stream(productDtoList)
                .map((product) -> product.toProduct()).toList();
    }

    @Override
    public Product saveProduct(String title, String description, String image, String category, double price) {
        ProductDto productDto = new ProductDto();
        productDto.setTitle(title);
        productDto.setDescription(description);
        productDto.setImage(image);
        productDto.setCategory(category);
        productDto.setPrice(price);

        ProductDto responseDto = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                productDto,
                ProductDto.class
        );

        return responseDto.toProduct();
    }

    @Override
    public Product updateProduct(long id, String title, String description, String image, String category, double price) {
        ProductDto requestProductDto = new ProductDto();
        requestProductDto.setId(id);
        requestProductDto.setTitle(title);
        requestProductDto.setDescription(description);
        requestProductDto.setImage(image);
        requestProductDto.setCategory(category);
        requestProductDto.setPrice(price);

        restTemplate.put("https://fakestoreapi.com/products/" + id, requestProductDto);
        return findProduct(id);
    }

    @Override
    public Product deleteProduct(long id) {
        Product deletedProduct = findProduct(id);
        restTemplate.delete("https://fakestoreapi.com/products/" + id);
        return deletedProduct;
    }
}
