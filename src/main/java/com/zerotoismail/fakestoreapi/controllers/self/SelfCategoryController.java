package com.zerotoismail.fakestoreapi.controllers.self;

import com.zerotoismail.fakestoreapi.common.Common;
import com.zerotoismail.fakestoreapi.dto.CategoryDto;
import com.zerotoismail.fakestoreapi.dto.selfDto.CategoryResponseDto;
import com.zerotoismail.fakestoreapi.dto.selfDto.ProductResponseDto;
import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;
import com.zerotoismail.fakestoreapi.services.ICategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SelfCategoryController {

    private ICategoryService categoryService;
    public SelfCategoryController(@Qualifier("selfCategoryServiceImpl") ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategory(){
        List<Category> categories = categoryService.findAll();
        List<CategoryResponseDto> categoryResponseDtoList =
        categories
                .stream()
                .map((category) -> Common.toCategoryResponseDto(category, new CategoryResponseDto()))
                .toList();

        return new ResponseEntity<>(categoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponseDto>> getSpecificCategoryProducts(@PathVariable String category){
        List<Product> products = categoryService.findProductByCategory(category);

        List<ProductResponseDto> productResponseDtoList =
                products
                        .stream()
                        .map((product) -> Common.toProductResponseDto(product, new ProductResponseDto()))
                        .toList();

        return new ResponseEntity<>(productResponseDtoList, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.save(-1, categoryDto.getName());
        CategoryResponseDto categoryResponseDto = Common.toCategoryResponseDto(category, new CategoryResponseDto());
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> createCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto){
        Category category = categoryService.save(id, categoryDto.getName());
        CategoryResponseDto categoryResponseDto = Common.toCategoryResponseDto(category, new CategoryResponseDto());
        return new ResponseEntity<>(categoryResponseDto, HttpStatus.CREATED);
    }
}
