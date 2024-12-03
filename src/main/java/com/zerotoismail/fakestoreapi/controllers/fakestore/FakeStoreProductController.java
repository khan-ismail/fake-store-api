package com.zerotoismail.fakestoreapi.controllers.fakestore;

import com.zerotoismail.fakestoreapi.dto.ProductDto;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakeStore")
public class FakeStoreProductController {

    private IProductService productService;

    public FakeStoreProductController(@Qualifier("fakeStoreProductServiceImpl") IProductService fakeStoreService) {
        this.productService = fakeStoreService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable long id) {
        Product product  = productService.findProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        List<Product> products = productService.findAllProducts();
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto){
        Product product = productService.saveProduct(
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getCategory(),
                productDto.getPrice()
        );

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto){
        productDto.setId(id);
        Product product = productService.updateProduct(
                productDto.getId(),
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getCategory(),
                productDto.getPrice()
        );

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable long id){
        Product product = productService.deleteProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
