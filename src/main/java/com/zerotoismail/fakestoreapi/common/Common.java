package com.zerotoismail.fakestoreapi.common;

import com.zerotoismail.fakestoreapi.dto.selfDto.CategoryResponseDto;
import com.zerotoismail.fakestoreapi.dto.selfDto.ProductResponseDto;
import com.zerotoismail.fakestoreapi.models.Category;
import com.zerotoismail.fakestoreapi.models.Product;

public class Common {

    public static ProductResponseDto toProductResponseDto(Product product, ProductResponseDto productResponseDto) {
        productResponseDto.setId(product.getId());
        productResponseDto.setName(product.getTitle());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setImageUrl(product.getImageUrl());
        productResponseDto.setDescription(product.getDescription());

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setId(product.getCategory().getId());
        categoryResponseDto.setName(product.getCategory().getName());
        productResponseDto.setCategory(categoryResponseDto);
        return productResponseDto;
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category, CategoryResponseDto categoryResponseDto) {
        categoryResponseDto.setId(category.getId());
        categoryResponseDto.setName(category.getName());
        return categoryResponseDto;
    }
}
