package com.zerotoismail.fakestoreapi.dto.selfDto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto {
    private long id;
    private String name;
    private String description;
    private String imageUrl;
    private CategoryResponseDto category;
    private double price;
}
