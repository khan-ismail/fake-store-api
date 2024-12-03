package com.zerotoismail.fakestoreapi.controllers.self;


import com.zerotoismail.fakestoreapi.common.Common;
import com.zerotoismail.fakestoreapi.dto.ProductDto;
import com.zerotoismail.fakestoreapi.dto.selfDto.ProductResponseDto;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SelfProductController {


    private IProductService productService;

    public SelfProductController(@Qualifier("selfProductServiceImpl") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        Product product = productService.findProduct(id);
        ProductResponseDto productResponseDto = Common.toProductResponseDto(product, new ProductResponseDto());
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProduct() {
        List<Product> products = productService.findAllProducts();

        List<ProductResponseDto> productResponseDtoList = products
                .stream()
                .map((product) -> Common.toProductResponseDto(product, new ProductResponseDto())).toList();
        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        Product product =  productService.saveProduct(
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getCategory(),
                productDto.getPrice()
        );
        ProductResponseDto productResponseDto = Common.toProductResponseDto(product, new ProductResponseDto());
        return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable long id, @RequestBody ProductDto productDto) {
        Product product =  productService.updateProduct(
                id,
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getCategory(),
                productDto.getPrice()
        );
        ProductResponseDto productResponseDto = Common.toProductResponseDto(product, new ProductResponseDto());
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductResponseDto> deleteProduct(@PathVariable long id){
        Product product = productService.deleteProduct(id);

        ProductResponseDto productResponseDto = Common.toProductResponseDto(product, new ProductResponseDto());
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }
}
